package com.project.ems.controllers;

import com.project.ems.entity.Contact;
import com.project.ems.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the contact, ("/contact" for mapping)
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/contact")
public class ContactController {
    /**
     * object of ContactService, declared to use all the methods in that service.
     */
    @Autowired
    private ContactService contactService;

    /**
     * @return Contacts of the all employees as a list
     */
    @GetMapping("/findAll")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER-ADMIN')")
    List<Contact> findAll(){
        return contactService.findAll();
    }

    /**
     * @param id id of the particular contact that needs to find
     * @return Contact associated with the particular id
     */
    @GetMapping("find/{id}")
    Contact findContact(@PathVariable int id){
        return contactService.findById(id);
    }

    /**
     * @param contact Contact of the employee that need to be added
     * @return added contact with its id.
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasRole('SUPER-ADMIN')")
    Contact addContact(@RequestBody Contact contact){
        contact.setId(0);
        return contactService.save(contact);
    }

    /**
     * @param contact updated information of the employees contact along with its id
     * @return added contact with its id
     */
    @PutMapping("/update")
    Contact updateContact(@RequestBody Contact contact){
        return contactService.save(contact);
    }

    /**
     * @param id id of the contact that needs to be deleted
     * @return Show message regarding successful deletion of the particular contact
     */
    @DeleteMapping("/delete/{id}")
    String deleteContact(@PathVariable int id){
        contactService.delete(id);
        return "Deleted contact" +id;
    }
}
