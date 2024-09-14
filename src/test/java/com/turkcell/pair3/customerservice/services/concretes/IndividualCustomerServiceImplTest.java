package com.turkcell.pair3.customerservice.services.concretes;

import com.turkcell.pair3.customerservice.clients.AuthServiceClient;
import com.turkcell.pair3.customerservice.clients.InvoiceServiceClient;
import com.turkcell.pair3.customerservice.clients.OrderServiceClient;
import com.turkcell.pair3.customerservice.clients.ProductClient;
import com.turkcell.pair3.customerservice.enums.EnumGender;
import com.turkcell.pair3.customerservice.repositories.IndividualCustomerRepository;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.*;

class IndividualCustomerServiceImplTest {
    @Mock
    IndividualCustomerRepository individualCustomerRepository;
    @Mock
    InvoiceServiceClient invoiceServiceClient;
    @Mock
    OrderServiceClient orderServiceClient;
    @Mock
    AuthServiceClient authServiceClient;
    @Mock
    ProductClient productClient;
    @InjectMocks
    IndividualCustomerServiceImpl individualCustomerServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCustomer() {
        when(individualCustomerRepository.findByNationalityId(anyString())).thenReturn(null);
        when(authServiceClient.register(any())).thenReturn(Integer.valueOf(0));
        when(productClient.createCart(anyInt())).thenReturn(Integer.valueOf(0));

        IndividualCustomerAddResponse result = individualCustomerServiceImpl.saveCustomer(new IndividualCustomerAddRequest("email", "nationalityId", "gsmNumber", "firstName", "lastName", "secondName", "role", LocalDate.of(2024, Month.SEPTEMBER, 14), "gender", "fatherName", "motherName", "homePhone", "mobilePhone", "fax", "password", "username"));
        Assertions.assertEquals(new IndividualCustomerAddResponse(Integer.valueOf(0), "customerId", "email", "nationalityId", "gsmNumber", "firstName", "lastName", "secondName", "role", LocalDate.of(2024, Month.SEPTEMBER, 14), EnumGender.MALE, "fatherName", "motherName", "homePhone", "mobilePhone", "fax"), result);
    }

    @Test
    void testSearchCustomer() {
        when(individualCustomerRepository.search(any())).thenReturn(List.of(new IndividualCustomerSearchResponse("customerId", "firstName", "lastName", "secondName", "nationalityId")));

        List<IndividualCustomerSearchResponse> result = individualCustomerServiceImpl.searchCustomer(new IndividualCustomerSearchRequest("nationalityId", "customerId", "gsmNumber", "firstName", "lastName"));
        Assertions.assertEquals(List.of(new IndividualCustomerSearchResponse("customerId", "firstName", "lastName", "secondName", "nationalityId")), result);
    }

    @Test
    void testGetCustomerInfo() {
        when(individualCustomerRepository.findByCustomerId(anyString())).thenReturn(null);

        IndividualCustomerInfoResponse result = individualCustomerServiceImpl.getCustomerInfo("customerId");
        Assertions.assertEquals(new IndividualCustomerInfoResponse("firstName", "lastName", EnumGender.MALE, "motherName", "fatherName", "secondName", LocalDate.of(2024, Month.SEPTEMBER, 14), "nationalityId"), result);
    }

    @Test
    void testGetAll() {
        List<IndividualCustomerInfoResponse> result = individualCustomerServiceImpl.getAll(null);
        Assertions.assertEquals(List.of(new IndividualCustomerInfoResponse("firstName", "lastName", EnumGender.MALE, "motherName", "fatherName", "secondName", LocalDate.of(2024, Month.SEPTEMBER, 14), "nationalityId")), result);
    }

    @Test
    void testCheckNationalityId() {
        when(individualCustomerRepository.existsByNationalityId(anyString())).thenReturn(true);

        CheckNationalityIdResponse result = individualCustomerServiceImpl.checkNationalityId("nationalityId");
        Assertions.assertEquals(new CheckNationalityIdResponse(true), result);
    }

    @Test
    void testUpdateCustomer() {
        when(individualCustomerRepository.existsByNationalityId(anyString())).thenReturn(true);

        IndividualCustomerInfoResponse result = individualCustomerServiceImpl.updateCustomer(Integer.valueOf(0), new IndividualCustomerUpdateRequest("firstName", "lastName", EnumGender.MALE, "motherName", "fatherName", "secondName", LocalDate.of(2024, Month.SEPTEMBER, 14), "nationalityId"));
        Assertions.assertEquals(new IndividualCustomerInfoResponse("firstName", "lastName", EnumGender.MALE, "motherName", "fatherName", "secondName", LocalDate.of(2024, Month.SEPTEMBER, 14), "nationalityId"), result);
    }

    @Test
    void testDeleteCustomer() {
        when(individualCustomerRepository.findByCustomerId(anyString())).thenReturn(null);
        when(invoiceServiceClient.getAllInvoiceIds(anyInt())).thenReturn(List.of(Integer.valueOf(0)));
        when(orderServiceClient.getOrderIdsByBillAccountId(any())).thenReturn(List.of(new GregorianCalendar(2024, Calendar.SEPTEMBER, 14, 17, 40).getTime()));

        IndividualCustomerDeleteResponse result = individualCustomerServiceImpl.deleteCustomer("customerId");
        Assertions.assertEquals(new IndividualCustomerDeleteResponse("accountNumber", "email", "nationalityId", "gsmNumber", "firstName", "lastName", "secondName"), result);
    }

    @Test
    void testUpdateContact() {
        when(individualCustomerRepository.findByCustomerId(anyString())).thenReturn(null);

        individualCustomerServiceImpl.updateContact(new IndividualCustomerContactUpdateRequest("customerId", "email", "mobilePhone", "homePhone", "fax"));
    }
}