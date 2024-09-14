package com.turkcell.pair3.customerservice.services.mappers;

import com.turkcell.pair3.customerservice.entities.Address;
import com.turkcell.pair3.customerservice.entities.City;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.AddressUpdateResponse;
import com.turkcell.pair3.customerservice.services.mapper.AddressMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressMapperTest {

    private final AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);

    @Test
    public void testAddressFromAddRequest() {
        AddressAddRequest request = new AddressAddRequest();
        request.setCustomerId(1);
        request.setCityId(2);
        request.setHouseNumber(123);
        request.setDescription("Test Address");

        Address address = addressMapper.addressFromAddRequest(request);

        assertEquals(1, address.getCustomer().getId());
        assertEquals(2, address.getCity().getId());
        assertEquals(123, address.getHouseNumber());
        assertEquals("Test Address", address.getDescription());
        assertEquals(false, address.isPrimary()); // Check constant value
    }

    @Test
    public void testUpdateAddressField() {
        Address address = new Address();
        address.setHouseNumber(123);
        address.setDescription("Old Address");
        address.setPrimary(true);

        AddressUpdateRequest request = new AddressUpdateRequest();
        request.setHouseNumber(456);
        request.setDescription("Updated Address");

        addressMapper.updateAddressField(address, request);

        assertEquals(456, address.getHouseNumber());
        assertEquals("Updated Address", address.getDescription());
        assertEquals(true, address.isPrimary()); // Ensure this field is not changed as it was not mapped
    }

    @Test
    public void testAddressUpdateResponseFromAddress() {
        Address address = new Address();
        City city = new City();
        city.setId(2);
        address.setCity(city);
        address.setHouseNumber(123);
        address.setDescription("Test Address");

        AddressUpdateResponse response = addressMapper.addressUpdateResponseFromAddress(address);
        assertEquals(2, response.getCityId());
        assertEquals(123, response.getHouseNumber());
        assertEquals("Test Address", response.getDescription());
    }
}
