package com.turkcell.pair3.customerservice.controllers;

import com.turkcell.pair3.customerservice.services.abstracts.AddressService;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.AddressUpdateResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class AddressControllerTest {
    @Mock
    AddressService addressService;
    @InjectMocks
    AddressController addressController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        when(addressService.save(any())).thenReturn(Integer.valueOf(0));

        Integer result = addressController.save(new AddressAddRequest(Integer.valueOf(0), Integer.valueOf(0), "description", Integer.valueOf(0)));
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testDelete() {
        addressController.delete(Integer.valueOf(0));
    }

    @Test
    void testUpdate() {
        when(addressService.update(anyInt(), any())).thenReturn(new AddressUpdateResponse(Integer.valueOf(0), Integer.valueOf(0), "description", Integer.valueOf(0)));

        AddressUpdateResponse result = addressController.update(Integer.valueOf(0), new AddressUpdateRequest(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), "description", Integer.valueOf(0)));
        Assertions.assertEquals(new AddressUpdateResponse(Integer.valueOf(0), Integer.valueOf(0), "description", Integer.valueOf(0)), result);
    }

    @Test
    void testMakeAddressPrimary() {
        addressController.makeAddressPrimary(Integer.valueOf(0));
    }
}