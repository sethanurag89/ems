package com.project.ems.services;

import com.project.ems.entity.Address;
import com.project.ems.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * implementation of the AddressService interface
 */
@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    AddressServiceImpl(AddressRepository theAddressRepository){
        this.addressRepository = theAddressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(int id) {
        Optional<Address> res = addressRepository.findById(id);
        Address address = null;
        if(res.isPresent()){
            address = res.get();
        }
        else {
            // we didn't find the address
            throw new RuntimeException("Did not find id - " + id);
        }
        return address;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteById(int id) {
        addressRepository.deleteById(id);
    }
}
