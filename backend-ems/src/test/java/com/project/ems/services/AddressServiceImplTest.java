package com.project.ems.services;

import com.project.ems.controllers.AddressController;
import com.project.ems.entity.Address;
import com.project.ems.repositories.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = AddressRepository.class)
class AddressServiceImplTest {
//    @Autowired
    private AddressRepository addressRepository;
//    @Autowired
    private AddressService addressService;
//    @Autowired
    private Address address;

    @BeforeEach
    void setup(){
//        addressRepository = new AddressRepository();
        System.out.println("**************************************************************************************");
        addressService = new AddressServiceImpl(addressRepository);
//        System.out.println("1");
        address = new Address();
        address.setId(1);
        address.setAddressLine1("Jhansi");
        address.setAddressLine2("Jhansi");
        address.setCity("City");
        address.setState("State");
        address.setPincode("12345");
        address.setCreatedOn(LocalDateTime.now());
        address.setUpdatedOn(LocalDateTime.now());
        addressRepository.save(address);
//        addressService.save(address);
//        System.out.println(addressRepository.findById(1));
    }

    @Test
    void findAll() {
//        List<Address> addressList = new ArrayList<>();
//        addressList.add(address);
//        addressService.save(address);
//        System.out.println(addressService);
        List<Address> result = addressService.findAll();
//        assertEquals(addressList, result);
        System.out.println(result.size());
    }

//    @Test
//    void findById() {
//    }
//
//    @Test
//    void save() {
//    }
//
//    @Test
//    void deleteById() {
//    }
}