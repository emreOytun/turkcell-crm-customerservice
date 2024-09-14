package com.turkcell.pair3.customerservice.services.concretes;

import com.turkcell.pair3.core.exception.types.BusinessException;
import com.turkcell.pair3.customerservice.entities.Address;
import com.turkcell.pair3.customerservice.entities.Customer;
import com.turkcell.pair3.customerservice.repositories.AddressRepository;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.AddressUpdateResponse;
import com.turkcell.pair3.customerservice.services.mapper.AddressMapper;
import com.turkcell.pair3.customerservice.services.messages.AddressMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressServiceImpl addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_whenNoPrimaryAddressExists() {
        AddressAddRequest request = new AddressAddRequest();
        Address address = new Address();
        address.setCustomer(new Customer());
        address.getCustomer().setId(1);
        address.setPrimary(true);

        when(addressMapper.addressFromAddRequest(any(AddressAddRequest.class))).thenReturn(address);
        when(addressRepository.existsPrimaryAddressByCustomerId(eq(1))).thenReturn(false);

        address.setId(1);
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Integer savedId = addressService.save(request);

        assertNotNull(savedId);
        assertEquals(address.getId(), savedId);
        verify(addressRepository).save(address);
    }

    @Test
    void testSave_whenPrimaryAddressExists() {
        AddressAddRequest request = new AddressAddRequest();
        Address address = new Address();
        address.setCustomer(new Customer());
        address.getCustomer().setId(1);

        when(addressMapper.addressFromAddRequest(any(AddressAddRequest.class))).thenReturn(address);
        when(addressRepository.existsPrimaryAddressByCustomerId(eq(1))).thenReturn(true);

        address.setId(1);
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Integer savedId = addressService.save(request);

        assertNotNull(savedId);
        assertEquals(address.getId(), savedId);
        verify(addressRepository).save(address);
    }

    @Test
    void testDelete_whenAddressExists() {
        Integer id = 1;

        when(addressRepository.existsById(eq(id))).thenReturn(true);

        addressService.delete(id);

        verify(addressRepository).deleteById(eq(id));
    }

    @Test
    void testDelete_whenAddressDoesNotExist() {
        Integer id = 1;

        when(addressRepository.existsById(eq(id))).thenReturn(false);

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> addressService.delete(id)
        );

        assertEquals(AddressMessages.NO_ADDRESS_FOUND, exception.getMessage());
        verify(addressRepository, never()).deleteById(eq(id));
    }

    @Test
    void testUpdate_whenAddressExists() {
        Integer id = 1;
        AddressUpdateRequest request = new AddressUpdateRequest();
        Address address = new Address();
        AddressUpdateResponse response = new AddressUpdateResponse();

        when(addressRepository.findById(eq(id))).thenReturn(Optional.of(address));
        // No need to set a return value for updateAddressField, as it's void
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        when(addressMapper.addressUpdateResponseFromAddress(any(Address.class))).thenReturn(response);

        AddressUpdateResponse updatedResponse = addressService.update(id, request);

        assertNotNull(updatedResponse);
        assertEquals(response, updatedResponse);
        verify(addressMapper).updateAddressField(eq(address), eq(request)); // Verify that the method is called
        verify(addressRepository).save(address);
    }

    @Test
    void testUpdate_whenAddressDoesNotExist() {
        Integer id = 1;
        AddressUpdateRequest request = new AddressUpdateRequest();

        when(addressRepository.findById(eq(id))).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> addressService.update(id, request)
        );

        assertEquals(AddressMessages.NO_ADDRESS_FOUND, exception.getMessage());
        verify(addressRepository, never()).save(any(Address.class));
    }

    @Test
    void testMakePrimary() {
        Integer newPrimaryAddressId = 1;
        Integer customerId = 2;

        when(addressRepository.findFirstCustomerIdByAddressId(eq(newPrimaryAddressId))).thenReturn(Optional.of(customerId));

        addressService.makePrimary(newPrimaryAddressId);

        verify(addressRepository).removeAllPrimaryAddressesByCustomerId(eq(customerId));
        verify(addressRepository).markAsPrimary(eq(newPrimaryAddressId));
    }

    @Test
    void testMakePrimary_whenCustomerIdNotFound() {
        Integer newPrimaryAddressId = 1;

        when(addressRepository.findFirstCustomerIdByAddressId(eq(newPrimaryAddressId))).thenReturn(Optional.empty());

        addressService.makePrimary(newPrimaryAddressId);

        verify(addressRepository, times(1)).removeAllPrimaryAddressesByCustomerId(any());
        verify(addressRepository).markAsPrimary(eq(newPrimaryAddressId));
    }
}
