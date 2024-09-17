package com.project.ems.repositories;

import com.project.ems.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contactrepository extending Jpa repository ot perform crud operations.
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
