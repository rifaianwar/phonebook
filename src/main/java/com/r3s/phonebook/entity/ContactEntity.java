package com.r3s.phonebook.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

import java.util.Date;

@Data
@Entity
@Table(name = "contact")
//@JsonIgnoreProperties(value = { "createdDate", "updatedDate", "deletedDate", "id" })
@SQLRestriction(value = "deleted_date is null")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_contact")
    @SequenceGenerator(name = "sq_contact", sequenceName = "sq_contact", allocationSize = 1)
    private Long id;
    private Date createdDate;
    private Date updatedDate;
    private Date deletedDate;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String note;
    private String grup;

}
