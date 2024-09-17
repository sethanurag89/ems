package com.project.ems.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    private Address address;

    @BeforeEach
    public void setup() {
        address = new Address();
    }
    @Test
    public void testGettersAndSetters() {
        address.setId(1);
        address.setAddressLine1("Jhansi");
        address.setAddressLine2("Jhansi");
        address.setCity("City");
        address.setState("State");
        address.setPincode("12345");
        address.setCreatedOn(LocalDateTime.now());
        address.setUpdatedOn(LocalDateTime.now());
        int id = address.getId();
        String addressLine1 = address.getAddressLine1();
        String addressLine2 = address.getAddressLine2();
        String city = address.getCity();
        String state = address.getState();
        String pincode = address.getPincode();
        LocalDateTime createdOn = address.getCreatedOn();
        LocalDateTime updatedOn = address.getUpdatedOn();

        Assertions.assertEquals(1, id);
        Assertions.assertEquals("Jhansi", addressLine1);
        Assertions.assertEquals("Jhansi", addressLine2);
        Assertions.assertEquals("City", city);
        Assertions.assertEquals("State", state);
        Assertions.assertEquals("12345", pincode);
        Assertions.assertNotNull(createdOn);
        Assertions.assertNotNull(updatedOn);

    }

    @Test
    public void testEquals() {
        Address address1 = new Address();
        Address address2 = new Address();
        Assertions.assertEquals(address1.equals(address2), true);
    }

    @Test
    public void testToString() {
        String toString = address.toString();
        Assertions.assertNotNull(toString);
        Assertions.assertNotEquals("", toString);
    }

}