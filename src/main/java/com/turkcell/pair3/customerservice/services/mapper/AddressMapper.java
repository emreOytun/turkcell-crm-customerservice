package com.turkcell.pair3.customerservice.services.mapper;

import com.turkcell.pair3.customerservice.entities.Address;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.AddressUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(target = "customer.id", source = "request.customerId")
    @Mapping(target = "city.id", source = "request.cityId")
    @Mapping(target = "primary", constant = "false")
    Address addressFromAddRequest(AddressAddRequest request);

    @Mapping(target="id", ignore=true)
    void updateAddressField(@MappingTarget Address address, AddressUpdateRequest request);

    AddressUpdateResponse addressUpdateResponseFromAddress(Address address);
}
