package com.turkcell.pair3.customerservice.controllers;

import com.turkcell.pair3.customerservice.services.abstracts.IndividualCustomerService;
import com.turkcell.pair3.customerservice.services.dtos.requests.*;
import com.turkcell.pair3.customerservice.services.dtos.responses.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private IndividualCustomerService individualCustomerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        IndividualCustomerAddRequest request = new IndividualCustomerAddRequest(/* add necessary fields */);
        IndividualCustomerAddResponse expectedResponse = new IndividualCustomerAddResponse(/* add necessary fields */);
        when(individualCustomerService.saveCustomer(any(IndividualCustomerAddRequest.class))).thenReturn(expectedResponse);

        IndividualCustomerAddResponse response = customerController.save(request);
        assertNotNull(response);
        assertEquals(expectedResponse, response);
    }

    @Test
    void testSearchCustomer() {
        IndividualCustomerSearchRequest request = new IndividualCustomerSearchRequest(/* add necessary fields */);
        List<IndividualCustomerSearchResponse> expectedResponse = Collections.singletonList(
                new IndividualCustomerSearchResponse(/* add necessary fields */)
        );
        when(individualCustomerService.searchCustomer(any(IndividualCustomerSearchRequest.class))).thenReturn(expectedResponse);

        List<IndividualCustomerSearchResponse> response = customerController.searchCustomer(request);
        assertNotNull(response);
        assertEquals(expectedResponse, response);
    }

    @Test
    void testGetInfo() {
        String customerId = "1234";
        IndividualCustomerInfoResponse expectedResponse = new IndividualCustomerInfoResponse(/* add necessary fields */);
        when(individualCustomerService.getCustomerInfo(eq(customerId))).thenReturn(expectedResponse);

        IndividualCustomerInfoResponse response = customerController.getInfo(customerId);
        assertNotNull(response);
        assertEquals(expectedResponse, response);
    }

    @Test
    void testUpdateCustomer() {
        Integer customerId = 1;
        IndividualCustomerUpdateRequest request = new IndividualCustomerUpdateRequest(/* add necessary fields */);
        IndividualCustomerInfoResponse expectedResponse = new IndividualCustomerInfoResponse(/* add necessary fields */);
        when(individualCustomerService.updateCustomer(eq(customerId), any(IndividualCustomerUpdateRequest.class)))
                .thenReturn(expectedResponse);

        IndividualCustomerInfoResponse response = customerController.updateCustomer(customerId, request);
        assertNotNull(response);
        assertEquals(expectedResponse, response);
    }

    @Test
    void testCheckNationalityId() {
        String nationalityId = "123456789";
        CheckNationalityIdResponse expectedResponse = new CheckNationalityIdResponse(/* add necessary fields */);
        when(individualCustomerService.checkNationalityId(eq(nationalityId))).thenReturn(expectedResponse);

        CheckNationalityIdResponse response = customerController.checkNationalityId(nationalityId);
        assertNotNull(response);
        assertEquals(expectedResponse, response);
    }

    @Test
    void testDeleteCustomer() {
        String customerId = "1234";
        IndividualCustomerDeleteResponse expectedResponse = new IndividualCustomerDeleteResponse(/* add necessary fields */);
        when(individualCustomerService.deleteCustomer(eq(customerId))).thenReturn(expectedResponse);

        IndividualCustomerDeleteResponse response = customerController.deleteCustomer(customerId);
        assertNotNull(response);
        assertEquals(expectedResponse, response);
    }

    @Test
    void testUpdateContact() {
        IndividualCustomerContactUpdateRequest request = new IndividualCustomerContactUpdateRequest(/* add necessary fields */);
        doNothing().when(individualCustomerService).updateContact(any(IndividualCustomerContactUpdateRequest.class));

        assertDoesNotThrow(() -> customerController.updateContact(request));
        verify(individualCustomerService).updateContact(eq(request));
    }
}
