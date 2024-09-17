package com.project.ems.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * entity for address table used to store address of the employee, one-to-one relationship with the employee table.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="address_info")
public class Address {
    /**
     * used to store contact id of the address.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "pincode", nullable = false)
    private String pincode;


    /**
     * one-to-one relationship with employee table.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empid",referencedColumnName = "id")
    @JsonBackReference
    private EmpDetails empDetails;

    @CreationTimestamp
    @Column(name="created_on", updatable = false)
    private LocalDateTime createdOn;

    /**
     * used to store last updated time of the address details
     */
    @UpdateTimestamp
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
}
