package com.project.ems.repositories;

import com.project.ems.entity.EmpDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Employeerepository extending Jpa repository ot perform crud operations.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmpDetails, Integer> {
    Optional<EmpDetails> findByEmail(String email);
}
