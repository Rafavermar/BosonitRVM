package com.block7crudvalidation.block7crudvalidation.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persona")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;

    @Column(length  = 10, nullable=false)
    private String usuario;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String surname;

    @Column(nullable = false)
    private String companyEmail;

    @Column(nullable = false)
    private String personalEmail;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private Date createdDate;

    @Column(name = "imagen_url", nullable = false)
    private String imageUrl;

    @Column(name = "termination_date", nullable = false)
    private Date terminationDate;
}
