package com.turkcell.pair3.customerservice.services.concretes;

import com.turkcell.pair3.customerservice.repositories.AddressRepository;
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

class AddressServiceImplTest {
    @Mock
    AddressRepository addressRepository;
    @InjectMocks
    AddressServiceImpl addressServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        when(addressRepository.existsPrimaryAddressByCustomerId(anyInt())).thenReturn(true);

        Integer result = addressServiceImpl.save(new AddressAddRequest(Integer.valueOf(0), Integer.valueOf(0), "description", Integer.valueOf(0)));
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testDelete() {
        addressServiceImpl.delete(Integer.valueOf(0));
    }

    @Test
    void testUpdate() {
        AddressUpdateResponse result = addressServiceImpl.update(0, new AddressUpdateRequest(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), "description", Integer.valueOf(0)));
        Assertions.assertEquals(new AddressUpdateResponse(Integer.valueOf(0), Integer.valueOf(0), "description", Integer.valueOf(0)), result);
    }

    @Test
    void testMakePrimary() {
        when(addressRepository.findFirstCustomerIdByAddressId(anyInt())).thenReturn(null);

        addressServiceImpl.makePrimary(Integer.valueOf(0));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme