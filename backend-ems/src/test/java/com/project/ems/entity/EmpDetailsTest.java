package com.project.ems.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EmpDetailsTest {

    @Test
    public void testIsActive() {
        EmpDetails empDetails = new EmpDetails();
        empDetails.setActive(true);
        boolean isActive = empDetails.isActive();
        Assertions.assertTrue(isActive);
    }

    @Test
    public void testGettersAndSetters() {
        // Create an EmpDetails object
        EmpDetails empDetails = new EmpDetails();
        //set value
        empDetails.setId(1);
        empDetails.setEmpId(1);
        empDetails.setFirstName("Anurag");
        empDetails.setLastName("Seth");
        empDetails.setEmail("aseth@argusoft.com");
        empDetails.setPassword("password");
        empDetails.setRole("ADMIN");
        empDetails.setBloodGroup("AB+");
        empDetails.setGender("Male");
        empDetails.setActive(true);
        empDetails.setMartialStatus("Bachelor");
        empDetails.setDob("25-07-2001");
        empDetails.setCreatedOn(LocalDateTime.now());
        empDetails.setUpdatedOn(LocalDateTime.now());
        empDetails.setCreatedBy(1);

        int id = empDetails.getId();
        int empId = empDetails.getEmpId();
        String firstName = empDetails.getFirstName();
        String lastName = empDetails.getLastName();
        String email = empDetails.getEmail();
        String password = empDetails.getPassword();
        String role = empDetails.getRole();
        String bloodGroup = empDetails.getBloodGroup();
        String gender = empDetails.getGender();
        boolean isActive = empDetails.isActive();
        String martialStatus = empDetails.getMartialStatus();
        String dob = empDetails.getDob();
        LocalDateTime createdOn = empDetails.getCreatedOn();
        LocalDateTime updatedOn = empDetails.getUpdatedOn();
        int createdBy = empDetails.getCreatedBy();

        //compare
        Assertions.assertEquals(1, id);
        Assertions.assertEquals(1, empId);
        Assertions.assertEquals("Anurag", firstName);
        Assertions.assertEquals("Seth", lastName);
        Assertions.assertEquals("aseth@argusoft.com", email);
        Assertions.assertEquals("password", password);
        Assertions.assertEquals("ADMIN", role);
        Assertions.assertEquals("AB+", bloodGroup);
        Assertions.assertEquals("Male", gender);
        Assertions.assertTrue(isActive);
        Assertions.assertEquals("Bachelor", martialStatus);
        Assertions.assertEquals("25-07-2001", dob);
        Assertions.assertNotNull(createdOn);
        Assertions.assertNotNull(updatedOn);
        Assertions.assertEquals(1, createdBy);
    }

    @Test
    public void testEquals() {
        EmpDetails emp = new EmpDetails();
        Assertions.assertEquals(emp.equals(emp), true);
        String toString = emp.toString();
        Assertions.assertNotNull(toString);
        Assertions.assertNotEquals("", toString);
    }
}