package com.turkcell.pair3.customerservice.services.mapper;

import com.turkcell.pair3.customerservice.entities.IndividualCustomer;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerContactUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.IndividualCustomerAddResponse;
import com.turkcell.pair3.customerservice.services.dtos.responses.IndividualCustomerContactUpdateResponse;
import com.turkcell.pair3.customerservice.services.dtos.responses.IndividualCustomerDeleteResponse;
import com.turkcell.pair3.customerservice.services.dtos.responses.IndividualCustomerInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface IndividualCustomerMapper {
    IndividualCustomerMapper INSTANCE = Mappers.getMapper(IndividualCustomerMapper.class);

    IndividualCustomer individualCustomerFromAddRequest(IndividualCustomerAddRequest request);

    @Mapping(target="id", ignore=true)
    void updateIndividualCustomerField(@MappingTarget IndividualCustomer customer, IndividualCustomerUpdateRequest request);

    List<IndividualCustomerInfoResponse> individualCustomerInfoResponsesFromCustomers(List<IndividualCustomer> customers);

    IndividualCustomerInfoResponse individualCustomerInfoResponseFromCustomer(IndividualCustomer customer);

    IndividualCustomerAddResponse individualCustomerAddResponseFromCustomer(IndividualCustomer customer);

    IndividualCustomerDeleteResponse individualCustomerDeleteResponseFromCustomer(IndividualCustomer individualCustomer);

}
