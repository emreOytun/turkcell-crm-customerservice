package com.turkcell.pair3.customerservice.controllers;

import com.turkcell.pair3.customerservice.services.abstracts.AddressService;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.AddressUpdateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        AddressAddRequest request = new AddressAddRequest(/* add necessary fields */);
        when(addressService.save(any(AddressAddRequest.class))).thenReturn(1);

        Integer result = addressController.save(request);
        assertNotNull(result);
        assertEquals(1, result);
    }

    @Test
    void testDelete() {
        Integer addressId = 1;
        doNothing().when(addressService).delete(addressId);

        assertDoesNotThrow(() -> addressController.delete(addressId));
        Mockito.verify(addressService).delete(eq(addressId));
    }

    @Test
    void testUpdate() {
        Integer addressId = 1;
        AddressUpdateRequest updateRequest = new AddressUpdateRequest(/* add necessary fields */);
        AddressUpdateResponse expectedResponse = new AddressUpdateResponse(/* add necessary fields */);
        when(addressService.update(eq(addressId), any(AddressUpdateRequest.class))).thenReturn(expectedResponse);

        AddressUpdateResponse response = addressController.update(addressId, updateRequest);
        assertNotNull(response);
        assertEquals(expectedResponse, response);
    }

    @Test
    void testMakeAddressPrimary() {
        Integer addressId = 1;
        doNothing().when(addressService).makePrimary(addressId);

        assertDoesNotThrow(() -> addressController.makeAddressPrimary(addressId));
        Mockito.verify(addressService).makePrimary(eq(addressId));
    }
}
