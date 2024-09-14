package com.turkcell.pair3.customerservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    private Address address;
    private City city;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        city = new City();
        city.setId(1);
        city.setCityName("Sample City");

        customer = new Customer();
        customer.setId(1);

        address = new Address();
        address.setId(1);
        address.setCity(city);
        address.setHouseNumber(123);
        address.setDescription("Sample Address");
        address.setCustomer(customer);
        address.setPrimary(true);
    }

    @Test
    public void testAddressInitialization() {
        assertNotNull(address);
        assertEquals(1, address.getId());
        assertEquals(city, address.getCity());
        assertEquals(123, address.getHouseNumber());
        assertEquals("Sample Address", address.getDescription());
        assertEquals(customer, address.getCustomer());
        assertTrue(address.isPrimary());
    }

    @Test
    public void testAddressSettersAndGetters() {
        address.setHouseNumber(456);
        address.setDescription("Updated Address");
        address.setPrimary(false);

        assertEquals(456, address.getHouseNumber());
        assertEquals("Updated Address", address.getDescription());
        assertFalse(address.isPrimary());
    }

    @Test
    public void testAddressConstructor() {
        Address newAddress = new Address(2, city, 789, "Another Address", customer, true);

        assertNotNull(newAddress);
        assertEquals(2, newAddress.getId());
        assertEquals(city, newAddress.getCity());
        assertEquals(789, newAddress.getHouseNumber());
        assertEquals("Another Address", newAddress.getDescription());
        assertEquals(customer, newAddress.getCustomer());
        assertTrue(newAddress.isPrimary());
    }

    // Add any other tests as necessary to cover edge cases or specific logic if you add any methods to Address
}
