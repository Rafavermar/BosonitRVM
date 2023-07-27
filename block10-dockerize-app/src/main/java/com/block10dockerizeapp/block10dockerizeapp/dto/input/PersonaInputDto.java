package com.block10dockerizeapp.block10dockerizeapp.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaInputDto {
    private Integer id;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagenUrl;
    private Date termination_date;
}
