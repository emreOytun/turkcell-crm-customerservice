package com.turkcell.pair3.customerservice.entities;

import com.turkcell.pair3.customerservice.entities.Customer;
import com.turkcell.pair3.customerservice.entities.Address;
import com.turkcell.pair3.core.enums.EnumState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1);
        customer.setCustomerId("CUST-001");
        customer.setGsmNumber("123456789");
        customer.setFax("123-456-7890");
        customer.setState(EnumState.ACTIVE);
        customer.setUserId(1001);
        customer.setAddress(Collections.emptyList()); // Initialize with an empty list
    }

    @Test
    public void testCustomerInitialization() {
        assertNotNull(customer);
        assertEquals(1, customer.getId());
        assertEquals("CUST-001", customer.getCustomerId());
        assertEquals("123456789", customer.getGsmNumber());
        assertEquals("123-456-7890", customer.getFax());
        assertEquals(EnumState.ACTIVE, customer.getState());
        assertEquals(1001, customer.getUserId());
        assertNotNull(customer.getAddress());
        assertTrue(customer.getAddress().isEmpty());
    }

    @Test
    public void testCustomerSettersAndGetters() {
        Address address = new Address();
        customer.setAddress(Collections.singletonList(address));

        assertEquals(1, customer.getAddress().size());
        assertEquals(address, customer.getAddress().get(0));
    }

    @Test
    public void testCustomerConstructor() {
        Customer newCustomer = new Customer(2, "CUST-002", "987654321", "098-765-4321", EnumState.PASSIVE, Collections.emptyList(), 1002);

        assertNotNull(newCustomer);
        assertEquals(2, newCustomer.getId());
        assertEquals("CUST-002", newCustomer.getCustomerId());
        assertEquals("987654321", newCustomer.getGsmNumber());
        assertEquals("098-765-4321", newCustomer.getFax());
        assertEquals(EnumState.PASSIVE, newCustomer.getState());
        assertEquals(1002, newCustomer.getUserId());
        assertNotNull(newCustomer.getAddress());
        assertTrue(newCustomer.getAddress().isEmpty());
    }
}
