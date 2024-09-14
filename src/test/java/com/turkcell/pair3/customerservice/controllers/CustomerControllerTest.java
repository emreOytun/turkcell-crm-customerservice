package com.turkcell.pair3.customerservice.controllers;

import com.turkcell.pair3.customerservice.enums.EnumGender;
import com.turkcell.pair3.customerservice.services.abstracts.IndividualCustomerService;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerContactUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerSearchRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

class CustomerControllerTest {
    @Mock
    IndividualCustomerService individualCustomerService;
    @InjectMocks
    CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        when(individualCustomerService.saveCustomer(any())).thenReturn(new IndividualCustomerAddResponse(Integer.valueOf(0), "customerId", "email", "nationalityId", "gsmNumber", "firstName", "lastName", "secondName", "role", LocalDate.of(2024, Month.SEPTEMBER, 14), EnumGender.MALE, "fatherName", "motherName", "homePhone", "mobilePhone", "fax"));

        IndividualCustomerAddResponse result = customerController.save(new IndividualCustomerAddRequest("email", "nationalityId", "gsmNumber", "firstName", "lastName", "secondName", "role", LocalDate.of(2024, Month.SEPTEMBER, 14), "gender", "fatherName", "motherName", "homePhone", "mobilePhone", "fax", "password", "username"));
        Assertions.assertEquals(new IndividualCustomerAddResponse(Integer.valueOf(0), "customerId", "email", "nationalityId", "gsmNumber", "firstName", "lastName", "secondName", "role", LocalDate.of(2024, Month.SEPTEMBER, 14), EnumGender.MALE, "fatherName", "motherName", "homePhone", "mobilePhone", "fax"), result);
    }

    @Test
    void testSearchCustomer() {
        when(individualCustomerService.searchCustomer(any())).thenReturn(List.of(new IndividualCustomerSearchResponse("customerId", "firstName", "lastName", "secondName", "nationalityId")));

        List<IndividualCustomerSearchResponse> result = customerController.searchCustomer(new IndividualCustomerSearchRequest("nationalityId", "customerId", "gsmNumber", "firstName", "lastName"));
        Assertions.assertEquals(List.of(new IndividualCustomerSearchResponse("customerId", "firstName", "lastName", "secondName", "nationalityId")), result);
    }

    @Test
    void testGetInfo() {
        when(individualCustomerService.getCustomerInfo(anyString())).thenReturn(new IndividualCustomerInfoResponse("firstName", "lastName", EnumGender.MALE, "motherName", "fatherName", "secondName", LocalDate.of(2024, Month.SEPTEMBER, 14), "nationalityId"));

        IndividualCustomerInfoResponse result = customerController.getInfo("customerId");
        Assertions.assertEquals(new IndividualCustomerInfoResponse("firstName", "lastName", EnumGender.MALE, "motherName", "fatherName", "secondName", LocalDate.of(2024, Month.SEPTEMBER, 14), "nationalityId"), result);
    }

    @Test
    void testUpdateCustomer() {
        when(individualCustomerService.updateCustomer(anyInt(), any())).thenReturn(new IndividualCustomerInfoResponse("firstName", "lastName", EnumGender.MALE, "motherName", "fatherName", "secondName", LocalDate.of(2024, Month.SEPTEMBER, 14), "nationalityId"));

        IndividualCustomerInfoResponse result = customerController.updateCustomer(Integer.valueOf(0), new IndividualCustomerUpdateRequest("firstName", "lastName", EnumGender.MALE, "motherName", "fatherName", "secondName", LocalDate.of(2024, Month.SEPTEMBER, 14), "nationalityId"));
        Assertions.assertEquals(new IndividualCustomerInfoResponse("firstName", "lastName", EnumGender.MALE, "motherName", "fatherName", "secondName", LocalDate.of(2024, Month.SEPTEMBER, 14), "nationalityId"), result);
    }

    @Test
    void testCheckNationalityId() {
        when(individualCustomerService.checkNationalityId(anyString())).thenReturn(new CheckNationalityIdResponse(true));

        CheckNationalityIdResponse result = customerController.checkNationalityId("nationalityId");
        Assertions.assertEquals(new CheckNationalityIdResponse(true), result);
    }

    @Test
    void testDeleteCustomer() {
        when(individualCustomerService.deleteCustomer(anyString())).thenReturn(new IndividualCustomerDeleteResponse("accountNumber", "email", "nationalityId", "gsmNumber", "firstName", "lastName", "secondName"));

        IndividualCustomerDeleteResponse result = customerController.deleteCustomer("customerId");
        Assertions.assertEquals(new IndividualCustomerDeleteResponse("accountNumber", "email", "nationalityId", "gsmNumber", "firstName", "lastName", "secondName"), result);
    }

    @Test
    void testUpdateContact() {
        customerController.updateContact(new IndividualCustomerContactUpdateRequest("customerId", "email", "mobilePhone", "homePhone", "fax"));
    }
}