package com.turkcell.pair3.customerservice.services.abstracts;

import com.turkcell.pair3.customerservice.core.business.paging.SearchByPageRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerContactUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerSearchRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.*;

import java.util.List;

public interface IndividualCustomerService {
    IndividualCustomerAddResponse saveCustomer(IndividualCustomerAddRequest individualCustomerAddRequest);
    IndividualCustomerInfoResponse getCustomerInfo(String customerId);
    List<IndividualCustomerSearchResponse> searchCustomer(IndividualCustomerSearchRequest request);
    IndividualCustomerInfoResponse updateCustomer(Integer id, IndividualCustomerUpdateRequest request);
    List<IndividualCustomerInfoResponse> getAll(SearchByPageRequest searchByPageRequest);
    CheckNationalityIdResponse checkNationalityId(String nationalityId);
    IndividualCustomerDeleteResponse deleteCustomer(String customerId);
    void updateContact(IndividualCustomerContactUpdateRequest request);
}
