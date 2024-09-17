//package com.project.ems.user;
//
//import com.project.ems.entity.EmpDetails;
//import com.project.ems.repositories.EmployeeRepository;
//import com.project.ems.services.EmployeeServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.security.core.userdetails.UserDetails;
//import java.time.LocalDateTime;
//
//class EmployeeInfoDetailsServiceTest {
//    @Autowired public EmployeeRepository employeeRepository;
//
//    private EmployeeInfoDetailsService employeeInfoDetailsService;
//    @BeforeEach
//    void setup(){
//        employeeInfoDetailsService = new EmployeeInfoDetailsService();
//
//    }
//    @Test
//    void loadUserByUsername() {
//        EmpDetails empDetails = new EmpDetails();
//        //set value
//        empDetails.setId(1);
//        empDetails.setEmpId(1);
//        empDetails.setFirstName("Anurag");
//        empDetails.setLastName("Seth");
//        empDetails.setEmail("aseth@argusoft.com");
//        empDetails.setPassword("password");
//        empDetails.setRole("ADMIN");
//        empDetails.setBloodGroup("AB+");
//        empDetails.setGender("Male");
//        empDetails.setActive(true);
//        empDetails.setMartialStatus("Bachelor");
//        empDetails.setDob("25-07-2001");
//        empDetails.setCreatedOn(LocalDateTime.now());
//        empDetails.setUpdatedOn(LocalDateTime.now());
//        empDetails.setCreatedBy(1);
//        employeeRepository.save(empDetails);
//        UserDetails emp = employeeInfoDetailsService.loadUserByUsername("aseth@argusoft.com");
//        Assertions.assertEquals("aseth@argusoft.com", emp.getUsername());
//    }
//}