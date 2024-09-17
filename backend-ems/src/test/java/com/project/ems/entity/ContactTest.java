package com.project.ems.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {
    @Test
    public void testGettersAndSetters() {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setContactType("Phone");
        contact.setNumber("1234567890");
        contact.setActive(true);
        contact.setCreatedOn(LocalDateTime.now());
        contact.setUpdatedOn(LocalDateTime.now());

        int id = contact.getId();
        String contactType = contact.getContactType();
        String number = contact.getNumber();
        boolean isActive = contact.isActive();
        LocalDateTime createdOn = contact.getCreatedOn();
        LocalDateTime updatedOn = contact.getUpdatedOn();

        Assertions.assertEquals(1, id);
        Assertions.assertEquals("Phone", contactType);
        Assertions.assertEquals("1234567890", number);
        Assertions.assertTrue(isActive);
        Assertions.assertNotNull(createdOn);
        Assertions.assertNotNull(updatedOn);
    }

    @Test
    public void testEquals() {
        Contact contact1 = new Contact();
        Contact contact2 = new Contact();
        Assertions.assertEquals(contact1.equals(contact2), true);
    }

    @Test
    public void testToString() {
        Contact contact = new Contact();
        String toString = contact.toString();
        Assertions.assertNotNull(toString);
        Assertions.assertNotEquals("", toString);
    }
}