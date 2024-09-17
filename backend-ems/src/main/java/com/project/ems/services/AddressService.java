package com.project.ems.services;

import com.project.ems.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface for Address having different method declaration for CRUD operations.
 */
@Service
public interface AddressService {
    /**
     * @return this method return list of all address.
     */
    List<Address> findAll();

    /**
     * @param id id of the address that needs to be searched
     * @return information of that particular id
     */
    Address findById(int id);

    /**
     * @param address body of the address of that particular entry
     * @return information of that particular address that is saved
     */
    Address save(Address address);

    /**
     * @param id id of the address that needs to be deleted
     */
    void deleteById(int id);
}
