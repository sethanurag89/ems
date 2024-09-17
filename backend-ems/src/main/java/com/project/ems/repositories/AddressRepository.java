package com.project.ems.repositories;

import com.project.ems.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Address repository extending Jpa repository ot perform crud operations.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
