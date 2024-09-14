package com.turkcell.pair3.customerservice.entities;

import com.turkcell.pair3.core.enums.EnumState;
import com.turkcell.pair3.customerservice.entities.IndividualCustomer;
import com.turkcell.pair3.customerservice.enums.EnumGender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class IndividualCustomerTest {

    private IndividualCustomer individualCustomer;

    @BeforeEach
    public void setUp() {
        individualCustomer = new IndividualCustomer();
        individualCustomer.setId(1);
        individualCustomer.setCustomerId("CUST-001");
        individualCustomer.setGsmNumber("123456789");
        individualCustomer.setFax("123-456-7890");
        individualCustomer.setState(EnumState.ACTIVE);
        individualCustomer.setUserId(1001);
        individualCustomer.setNationalityId("NAT-001");
        individualCustomer.setFirstName("John");
        individualCustomer.setLastName("Doe");
        individualCustomer.setSecondName("Middle");
        individualCustomer.setBirthDate(LocalDate.of(1990, 1, 1));
        individualCustomer.setGender(EnumGender.MALE);
        individualCustomer.setFatherName("Father Doe");
        individualCustomer.setMotherName("Mother Doe");
        individualCustomer.setHomePhone("555-1234");
    }

    @Test
    public void testIndividualCustomerInitialization() {
        assertNotNull(individualCustomer);
        assertEquals(1, individualCustomer.getId());
        assertEquals("CUST-001", individualCustomer.getCustomerId());
        assertEquals("123456789", individualCustomer.getGsmNumber());
        assertEquals("123-456-7890", individualCustomer.getFax());
        assertEquals(EnumState.ACTIVE, individualCustomer.getState());
        assertEquals(1001, individualCustomer.getUserId());
        assertEquals("NAT-001", individualCustomer.getNationalityId());
        assertEquals("John", individualCustomer.getFirstName());
        assertEquals("Doe", individualCustomer.getLastName());
        assertEquals("Middle", individualCustomer.getSecondName());
        assertEquals(LocalDate.of(1990, 1, 1), individualCustomer.getBirthDate());
        assertEquals(EnumGender.MALE, individualCustomer.getGender());
        assertEquals("Father Doe", individualCustomer.getFatherName());
        assertEquals("Mother Doe", individualCustomer.getMotherName());
        assertEquals("555-1234", individualCustomer.getHomePhone());
    }

    @Test
    public void testIndividualCustomerSettersAndGetters() {
        // Test the setter and getter for each field
        individualCustomer.setNationalityId("NAT-002");
        assertEquals("NAT-002", individualCustomer.getNationalityId());

        individualCustomer.setFirstName("Jane");
        assertEquals("Jane", individualCustomer.getFirstName());

        individualCustomer.setLastName("Smith");
        assertEquals("Smith", individualCustomer.getLastName());

        individualCustomer.setSecondName("Extra");
        assertEquals("Extra", individualCustomer.getSecondName());

        individualCustomer.setBirthDate(LocalDate.of(1985, 5, 15));
        assertEquals(LocalDate.of(1985, 5, 15), individualCustomer.getBirthDate());

        individualCustomer.setGender(EnumGender.FEMALE);
        assertEquals(EnumGender.FEMALE, individualCustomer.getGender());

        individualCustomer.setFatherName("John Smith");
        assertEquals("John Smith", individualCustomer.getFatherName());

        individualCustomer.setMotherName("Jane Smith");
        assertEquals("Jane Smith", individualCustomer.getMotherName());

        individualCustomer.setHomePhone("555-6789");
        assertEquals("555-6789", individualCustomer.getHomePhone());
    }
}
