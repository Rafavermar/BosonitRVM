package com.block13testingcrud.block13testingcrud.dto.output;

import com.block13testingcrud.block13testingcrud.dto.input.ProfesorInputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorFullOutputDto extends ProfesorInputDto {

    private Integer idPersona;
    private String usuario;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;

}
