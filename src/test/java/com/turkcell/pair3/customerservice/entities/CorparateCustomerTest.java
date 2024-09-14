package com.turkcell.pair3.customerservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CorparateCustomerTest {

    private CorparateCustomer corparateCustomer;

    @BeforeEach
    public void setUp() {
        corparateCustomer = new CorparateCustomer();
        corparateCustomer.setId(1);
        corparateCustomer.setCompanyName("Tech Corp");
    }

    @Test
    public void testCorparateCustomerInitialization() {
        assertNotNull(corparateCustomer);
        assertEquals(1, corparateCustomer.getId());
        assertEquals("Tech Corp", corparateCustomer.getCompanyName());
    }

    @Test
    public void testCorparateCustomerSettersAndGetters() {
        corparateCustomer.setCompanyName("Updated Tech Corp");

        assertEquals("Updated Tech Corp", corparateCustomer.getCompanyName());
    }

    @Test
    public void testCorparateCustomerConstructor() {
        CorparateCustomer newCorparateCustomer = new CorparateCustomer("Global Tech");

        assertNotNull(newCorparateCustomer);
        assertEquals("Global Tech", newCorparateCustomer.getCompanyName());
    }
}
