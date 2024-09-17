package com.project.ems.services;

import com.project.ems.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface for Contact having different method declaration for CRUD operations.
 */
@Service
public interface ContactService {
    /**
     * @return this method return list of all contacts.
     */
    List<Contact> findAll();

    /**
     * @param id id of the contact that needs to be searched
     * @return information of that particular id
     */
    Contact findById(int id);

    /**
     * @param contact body of the contact of that particular entry
     * @return information of that particular contact that is saved
     */
    Contact save(Contact contact);

    /**
     * @param id id of the contact that needs to be deleted
     */
    void delete(int id);
}
