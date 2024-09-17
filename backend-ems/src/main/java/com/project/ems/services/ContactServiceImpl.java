package com.project.ems.services;

import com.project.ems.entity.Contact;
import com.project.ems.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * implementation of the ContactService interface
 */
@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactServiceImpl(ContactRepository theContactRepository){
        this.contactRepository = theContactRepository;
    }
    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findById(int id) {
        Optional<Contact> res = contactRepository.findById(id);
        Contact contact = null;
        if(res.isPresent()){
            contact = res.get();
        }
        else {
            // we didn't find the contact
            throw new RuntimeException("Did not find id - " + id);
        }
        return contact;
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void delete(int id) {
        contactRepository.deleteById(id);
    }
}
