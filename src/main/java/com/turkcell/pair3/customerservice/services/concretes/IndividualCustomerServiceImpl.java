package com.turkcell.pair3.customerservice.services.concretes;

import com.turkcell.pair3.core.dtos.requests.SearchByPageRequest;
import com.turkcell.pair3.core.enums.EnumState;
import com.turkcell.pair3.core.events.factories.RegisterEventFactory;
import com.turkcell.pair3.core.exception.types.BusinessExceptionFactory;
import com.turkcell.pair3.core.services.CommonBusinessRules;
import com.turkcell.pair3.customerservice.clients.AuthServiceClient;
import com.turkcell.pair3.customerservice.clients.InvoiceServiceClient;
import com.turkcell.pair3.customerservice.clients.OrderServiceClient;
import com.turkcell.pair3.customerservice.clients.ProductClient;
import com.turkcell.pair3.customerservice.entities.IndividualCustomer;
import com.turkcell.pair3.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.pair3.customerservice.services.abstracts.IndividualCustomerService;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerContactUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerSearchRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.*;
import com.turkcell.pair3.customerservice.services.dtos.responses.factories.CheckNationalityIdResponseFactory;
import com.turkcell.pair3.customerservice.services.mapper.IndividualCustomerMapper;
import com.turkcell.pair3.customerservice.services.messages.CustomerMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IndividualCustomerServiceImpl implements IndividualCustomerService {
    private final IndividualCustomerRepository individualCustomerRepository;
    private final InvoiceServiceClient invoiceServiceClient;
    private final OrderServiceClient orderServiceClient;
    private final AuthServiceClient authServiceClient;
    private final ProductClient productClient;

    @Override
    public IndividualCustomerAddResponse saveCustomer(IndividualCustomerAddRequest individualCustomerAddRequest) {
        checkIfNationalityIdNotSavedBeforeOrThrowException(individualCustomerAddRequest.getNationalityId());

        IndividualCustomer customer = IndividualCustomerMapper.INSTANCE.individualCustomerFromAddRequest(individualCustomerAddRequest);
        customer.setCustomerId(UUID.randomUUID().toString());
        customer.setState(EnumState.ACTIVE);
        customer.setUserId(authServiceClient.register(RegisterEventFactory.create(individualCustomerAddRequest.getEmail(), individualCustomerAddRequest.getPassword())));
        individualCustomerRepository.save(customer);
        productClient.createCart(customer.getId());

        return IndividualCustomerMapper.INSTANCE.individualCustomerAddResponseFromCustomer(customer);
    }

    @Override
    public List<IndividualCustomerSearchResponse> searchCustomer(IndividualCustomerSearchRequest request) {
            List<IndividualCustomerSearchResponse> response = individualCustomerRepository.search(request);
            CommonBusinessRules.checkIfListEmptyOrThrowExceptionWithMessage(response, CustomerMessages.NO_CUSTOMER_FOUND);
            return response;
    }

    @Override
    public IndividualCustomerInfoResponse getCustomerInfo(String customerId) {
        return IndividualCustomerMapper.INSTANCE.individualCustomerInfoResponseFromCustomer(
                individualCustomerRepository.findByCustomerId(customerId).orElseThrow(
                        () -> BusinessExceptionFactory.createWithMessage(CustomerMessages.NO_CUSTOMER_FOUND)));
    }

    @Override
    public List<IndividualCustomerInfoResponse> getAll(SearchByPageRequest searchByPageRequest) {
        Pageable pageable = PageRequest.of(searchByPageRequest.getPageNo(), searchByPageRequest.getPageSize());
        return IndividualCustomerMapper.INSTANCE.individualCustomerInfoResponsesFromCustomers(
                individualCustomerRepository.findAll(pageable).stream().toList());
    }

    @Override
    public CheckNationalityIdResponse checkNationalityId(String nationalityId) {
        return CheckNationalityIdResponseFactory.createWithIfAlreadyExists(
                individualCustomerRepository.existsByNationalityId(nationalityId));
    }

    @Override
    public IndividualCustomerInfoResponse updateCustomer(Integer id, IndividualCustomerUpdateRequest request) {
        IndividualCustomer updatedCustomer = searchIndividualCustomerByIdOrThrowExceptionIfEmpty(id);

        if(individualCustomerRepository.existsByNationalityId(request.getNationalityId()) && !updatedCustomer.getNationalityId().equals(request.getNationalityId())) {
            throw BusinessExceptionFactory.createWithMessage(CustomerMessages.NATIONALITY_ID_ALREADY_EXISTS);
        }

        IndividualCustomerMapper.INSTANCE.updateIndividualCustomerField(updatedCustomer, request);
        return IndividualCustomerMapper.INSTANCE.individualCustomerInfoResponseFromCustomer(individualCustomerRepository.save(updatedCustomer));
    }

    @Override
    public IndividualCustomerDeleteResponse deleteCustomer(String customerId) {
        IndividualCustomer customer = searchIndividualCustomerByCustomerIdOrThrowExceptionIfEmpty(customerId);

        List<Integer> billAccountIdList = searchInvoiceIdsByCustomerIdOrThrowExceptionIfNoBillAccountFound(Integer.valueOf(customerId));
        List<Date> endDates = orderServiceClient.getOrderIdsByBillAccountId(billAccountIdList);
        checkOrderDatesAndThrowExceptionIfAnyActiveOrderFound(endDates);

        customer.setState(EnumState.PASSIVE);
        individualCustomerRepository.save(customer);
        return IndividualCustomerMapper.INSTANCE.individualCustomerDeleteResponseFromCustomer(customer);
    }

    @Override
    public void updateContact(IndividualCustomerContactUpdateRequest request) {
        IndividualCustomer updatedCustomer = searchIndividualCustomerByCustomerIdOrThrowExceptionIfEmpty(request.getCustomerId());
        authServiceClient.updateEmail(updatedCustomer.getUserId(), request.getEmail());
        updatedCustomer.setGsmNumber(request.getMobilePhone());
        updatedCustomer.setHomePhone(request.getHomePhone());
        updatedCustomer.setFax(request.getFax());
        individualCustomerRepository.save(updatedCustomer);
    }

    private void checkIfNationalityIdNotSavedBeforeOrThrowException(String nationalityId) {
        individualCustomerRepository.findByNationalityId(nationalityId)
                .ifPresent(c -> {throw BusinessExceptionFactory.createWithMessage(CustomerMessages.CUSTOMER_WITH_SAME_IDENTITY_EXISTS);});
    }

    private IndividualCustomer searchIndividualCustomerByIdOrThrowExceptionIfEmpty(Integer id) {
        return individualCustomerRepository.findById(id).orElseThrow(
                () -> BusinessExceptionFactory.createWithMessage(CustomerMessages.NO_CUSTOMER_FOUND));
    }

    private IndividualCustomer searchIndividualCustomerByCustomerIdOrThrowExceptionIfEmpty(String customerId) {
        return individualCustomerRepository.findByCustomerId(customerId).orElseThrow(
                () -> BusinessExceptionFactory.createWithMessage(CustomerMessages.NO_CUSTOMER_FOUND));
    }

    private List<Integer> searchInvoiceIdsByCustomerIdOrThrowExceptionIfNoBillAccountFound(Integer customerId) {
        List<Integer> billAccountIdList = invoiceServiceClient.getAllInvoiceIds(customerId);
        CommonBusinessRules.checkIfListEmptyOrThrowExceptionWithMessage(billAccountIdList, CustomerMessages.BILL_ACCOUNT_NOT_FOUND);
        return billAccountIdList;
    }

    private boolean checkIfThereIsAnyActiveOrders(List<Date> endDates) {
        Date now = new Date();
        return !CollectionUtils.isEmpty(endDates) & endDates.stream().anyMatch(endDate -> endDate.after(now));
    }

    private void checkOrderDatesAndThrowExceptionIfAnyActiveOrderFound(List<Date> endDates) {
        if (checkIfThereIsAnyActiveOrders(endDates)) {
            throw BusinessExceptionFactory.createWithMessage(CustomerMessages.ACTIVE_SERVICE_FOUND);
        }
    }
}
