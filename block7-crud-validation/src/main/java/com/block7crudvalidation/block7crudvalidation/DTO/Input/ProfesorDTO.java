package com.block7crudvalidation.block7crudvalidation.DTO.Input;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProfesorDTO {

private Integer idProfesor;
private PersonaDTO personaDTO;
private String comments;
private String branch;

}
