package com.turkcell.pair3.customerservice.services.mappers;

import com.turkcell.pair3.customerservice.entities.IndividualCustomer;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.IndividualCustomerAddResponse;
import com.turkcell.pair3.customerservice.services.dtos.responses.IndividualCustomerDeleteResponse;
import com.turkcell.pair3.customerservice.services.dtos.responses.IndividualCustomerInfoResponse;
import com.turkcell.pair3.customerservice.services.mapper.IndividualCustomerMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IndividualCustomerMapperTest {

    private final IndividualCustomerMapper mapper = Mappers.getMapper(IndividualCustomerMapper.class);

    @Test
    public void testIndividualCustomerFromAddRequest() {
        IndividualCustomerAddRequest request = new IndividualCustomerAddRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setNationalityId("ID12345");

        IndividualCustomer customer = mapper.individualCustomerFromAddRequest(request);

        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("ID12345", customer.getNationalityId());
    }

    @Test
    public void testUpdateIndividualCustomerField() {
        IndividualCustomer customer = new IndividualCustomer();
        customer.setFirstName("Jane");

        IndividualCustomerUpdateRequest request = new IndividualCustomerUpdateRequest();
        request.setFirstName("Janet");

        mapper.updateIndividualCustomerField(customer, request);

        assertEquals("Janet", customer.getFirstName());
    }

    @Test
    public void testIndividualCustomerInfoResponsesFromCustomers() {
        IndividualCustomer customer = new IndividualCustomer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");

        List<IndividualCustomer> customers = Collections.singletonList(customer);

        List<IndividualCustomerInfoResponse> responses = mapper.individualCustomerInfoResponsesFromCustomers(customers);

        assertEquals(1, responses.size());
        IndividualCustomerInfoResponse response = responses.get(0);
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
    }

    @Test
    public void testIndividualCustomerInfoResponseFromCustomer() {
        IndividualCustomer customer = new IndividualCustomer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");

        IndividualCustomerInfoResponse response = mapper.individualCustomerInfoResponseFromCustomer(customer);

        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
    }

    @Test
    public void testIndividualCustomerAddResponseFromCustomer() {
        IndividualCustomer customer = new IndividualCustomer();
        customer.setId(1);
        customer.setFirstName("John");

        IndividualCustomerAddResponse response = mapper.individualCustomerAddResponseFromCustomer(customer);

        assertEquals(1, response.getId());
        assertEquals("John", response.getFirstName());
    }

    @Test
    public void testIndividualCustomerDeleteResponseFromCustomer() {
        IndividualCustomer customer = new IndividualCustomer();
        customer.setId(1);
        customer.setFirstName("John");

        IndividualCustomerDeleteResponse response = mapper.individualCustomerDeleteResponseFromCustomer(customer);

        assertEquals("John", response.getFirstName());
    }
}
