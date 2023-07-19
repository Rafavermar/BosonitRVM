package com.block7crudvalidation.block7crudvalidation.Entities;

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
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersona;


    @Size(min = 6, max = 10)
    @NotNull
    private String usuario;

    @NotNull
    private String password;

    @NotNull
    private String name;

    private String surname;

    @NotNull
    private String companyEmail;

    @NotNull
    private String personalEmail;

    @NotNull
    private String city;

    @NotNull
    private boolean active;

    @NotNull
    private Date createdDate;

    @Column(name = "imagen_url")
    private String imageUrl;

    @Column(name = "termination_date")
    private Date terminationDate;


}
