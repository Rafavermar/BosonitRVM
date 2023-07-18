package com.block7crudvalidation.block7crudvalidation.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "persona")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private int idPersona;

    @Column(name = "usuario", nullable = false)
    @Size(min = 6, max = 10)
    private String usuario;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "company_email", nullable = false)
    private String companyEmail;

    @Column(name = "personal_email", nullable = false)
    private String personalEmail;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "imagen_url")
    private String imageUrl;

    @Column(name = "termination_date")
    private Date terminationDate;


}
