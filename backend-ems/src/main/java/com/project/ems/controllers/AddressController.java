package com.project.ems.controllers;

import com.project.ems.entity.Address;
import com.project.ems.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the address, ("/address" for mapping)
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/address")
public class AddressController {
    /**
     * object of AddressService, declared to use all the methods in that service.
     */
    @Autowired
    private AddressService addressService;

    /**
     * @return Address of the all employees as a list
     */
    @GetMapping("/findAll")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER-ADMIN')")
    public List<Address> findAll(){
        return addressService.findAll();
    }

    /**
     * @param id id of the particular address that needs to find
     * @return Address associated with the particular id
     */
    @GetMapping("/find/{id}")
    public Address findAddress(@PathVariable int id){
        return addressService.findById(id);
    }

    /**
     * @param address Address of the employee that need to be added
     * @return added address with its id.
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasRole('SUPER-ADMIN')")
    public Address addAddress(@RequestBody Address address){
        address.setId(0);
        return addressService.save(address);
    }

    /**
     * @param address updated information of the employees address along with its id
     * @return Updated address
     */
    @PutMapping("/update")
    public Address updateAddress(@RequestBody Address address){
        return addressService.save(address);
    }

    /**
     * @param id id of the address that needs to be deleted
     * @return Show message regarding successful deletion of the particular address
     */
    @DeleteMapping("/delete/{id}")
    public String deleteAddress(@PathVariable int id){
        addressService.deleteById(id);
        return "Deleted Address: " + id;
    }
}
