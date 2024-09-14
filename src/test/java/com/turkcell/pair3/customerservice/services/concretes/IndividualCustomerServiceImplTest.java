package com.turkcell.pair3.customerservice.services.concretes;

import com.turkcell.pair3.core.dtos.requests.SearchByPageRequest;
import com.turkcell.pair3.customerservice.clients.AuthServiceClient;
import com.turkcell.pair3.customerservice.clients.InvoiceServiceClient;
import com.turkcell.pair3.customerservice.clients.OrderServiceClient;
import com.turkcell.pair3.customerservice.clients.ProductClient;
import com.turkcell.pair3.customerservice.entities.IndividualCustomer;
import com.turkcell.pair3.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.pair3.customerservice.services.dtos.requests.*;
import com.turkcell.pair3.customerservice.services.dtos.responses.*;
import com.turkcell.pair3.customerservice.services.dtos.responses.factories.CheckNationalityIdResponseFactory;
import com.turkcell.pair3.customerservice.services.mapper.IndividualCustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class IndividualCustomerServiceImplTest {

    @Mock
    private IndividualCustomerRepository individualCustomerRepository;

    @Mock
    private AuthServiceClient authServiceClient;

    @Mock
    private ProductClient productClient;

    @Mock
    private InvoiceServiceClient invoiceServiceClient;

    @Mock
    private OrderServiceClient orderServiceClient;

    @Mock
    private IndividualCustomerMapper individualCustomerMapper;

    @InjectMocks
    private IndividualCustomerServiceImpl individualCustomerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCustomer() {
        IndividualCustomerAddRequest request = new IndividualCustomerAddRequest();
        IndividualCustomer customer = new IndividualCustomer();
        customer.setUserId(12345); // Setting userId as Integer

        when(individualCustomerMapper.individualCustomerFromAddRequest(request)).thenReturn(customer);
        when(authServiceClient.register(any())).thenReturn(1); // Mocking the register call
        when(individualCustomerRepository.save(customer)).thenReturn(customer);
        when(productClient.createCart(anyInt())).thenReturn(null); // Mocking createCart

        IndividualCustomerAddResponse response = individualCustomerService.saveCustomer(request);

        verify(individualCustomerRepository).save(customer);
        verify(productClient).createCart(eq(customer.getId())); // Verifying createCart is called
    }

    @Test
    public void testUpdateCustomer() {
        Integer id = 1;
        IndividualCustomerUpdateRequest request = new IndividualCustomerUpdateRequest();
        IndividualCustomer customer = new IndividualCustomer();
        customer.setUserId(12345); // Setting userId as Integer

        when(individualCustomerRepository.findById(id)).thenReturn(Optional.of(customer));
        doNothing().when(individualCustomerMapper).updateIndividualCustomerField(any(IndividualCustomer.class), any(IndividualCustomerUpdateRequest.class));
        when(individualCustomerRepository.save(customer)).thenReturn(customer);
        when(individualCustomerMapper.individualCustomerInfoResponseFromCustomer(customer)).thenReturn(new IndividualCustomerInfoResponse());

        IndividualCustomerInfoResponse response = individualCustomerService.updateCustomer(id, request);

        verify(individualCustomerMapper).updateIndividualCustomerField(any(IndividualCustomer.class), any(IndividualCustomerUpdateRequest.class));
        verify(individualCustomerRepository).save(customer);
    }

    @Test
    public void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        List<IndividualCustomer> customers = List.of(new IndividualCustomer());
        Page<IndividualCustomer> page = new PageImpl<>(customers);

        when(individualCustomerRepository.findAll(pageable)).thenReturn(page);
        when(individualCustomerMapper.individualCustomerInfoResponsesFromCustomers(anyList())).thenReturn(List.of(new IndividualCustomerInfoResponse()));

        List<IndividualCustomerInfoResponse> response = individualCustomerService.getAll(new SearchByPageRequest(0, 10));

        verify(individualCustomerRepository).findAll(pageable);
        verify(individualCustomerMapper).individualCustomerInfoResponsesFromCustomers(anyList());
    }

    @Test
    public void testSearchCustomer() {
        IndividualCustomerSearchRequest request = new IndividualCustomerSearchRequest();
        List<IndividualCustomerSearchResponse> searchResponses = List.of(new IndividualCustomerSearchResponse());

        when(individualCustomerRepository.search(request)).thenReturn(searchResponses);

        List<IndividualCustomerSearchResponse> response = individualCustomerService.searchCustomer(request);

        verify(individualCustomerRepository).search(request);
    }

    @Test
    public void testGetCustomerInfo() {
        String customerId = "12345";
        IndividualCustomer customer = new IndividualCustomer();
        IndividualCustomerInfoResponse infoResponse = new IndividualCustomerInfoResponse();

        when(individualCustomerRepository.findByCustomerId(customerId)).thenReturn(Optional.of(customer));
        when(individualCustomerMapper.individualCustomerInfoResponseFromCustomer(customer)).thenReturn(infoResponse);

        IndividualCustomerInfoResponse response = individualCustomerService.getCustomerInfo(customerId);

        verify(individualCustomerRepository).findByCustomerId(customerId);
        verify(individualCustomerMapper).individualCustomerInfoResponseFromCustomer(customer);
    }

    @Test
    public void testCheckNationalityId() {
        String nationalityId = "ID123";
        when(individualCustomerRepository.existsByNationalityId(nationalityId)).thenReturn(true);

        CheckNationalityIdResponse response = individualCustomerService.checkNationalityId(nationalityId);

        verify(individualCustomerRepository).existsByNationalityId(nationalityId);
    }

    @Test
    public void testDeleteCustomer() {
        String customerId = "12345";
        IndividualCustomer customer = new IndividualCustomer();
        List<Integer> billAccountIdList = List.of(1);
        List<Date> endDates = List.of(new Date(System.currentTimeMillis() - 10000));

        when(individualCustomerRepository.findByCustomerId(customerId)).thenReturn(Optional.of(customer));
        when(invoiceServiceClient.getAllInvoiceIds(anyInt())).thenReturn(billAccountIdList);
        when(orderServiceClient.getOrderIdsByBillAccountId(anyList())).thenReturn(endDates);
//        doNothing().when(individualCustomerRepository).save(any(IndividualCustomer.class));
        when(individualCustomerMapper.individualCustomerDeleteResponseFromCustomer(any(IndividualCustomer.class)))
                .thenReturn(new IndividualCustomerDeleteResponse());

        IndividualCustomerDeleteResponse response = individualCustomerService.deleteCustomer(customerId);

        verify(individualCustomerRepository).findByCustomerId(customerId);
        verify(invoiceServiceClient).getAllInvoiceIds(anyInt());
        verify(orderServiceClient).getOrderIdsByBillAccountId(anyList());
        verify(individualCustomerRepository).save(any(IndividualCustomer.class));
    }

    @Test
    public void testUpdateContact() {
        IndividualCustomerContactUpdateRequest request = new IndividualCustomerContactUpdateRequest();
        request.setEmail("aa@bb.com");
        IndividualCustomer customer = new IndividualCustomer();
        customer.setUserId(1);

        when(individualCustomerRepository.findByCustomerId(request.getCustomerId())).thenReturn(Optional.of(customer));
        doNothing().when(authServiceClient).updateEmail(anyInt(), anyString());
//        doNothing().when(individualCustomerRepository).save(any(IndividualCustomer.class));

        individualCustomerService.updateContact(request);

        verify(individualCustomerRepository).findByCustomerId(request.getCustomerId());
        verify(authServiceClient).updateEmail(anyInt(), anyString());
        verify(individualCustomerRepository).save(any(IndividualCustomer.class));
    }
}
