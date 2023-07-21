package com.block7crudvalidation.block7crudvalidation.DTO.Input;

import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
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
    private PersonaEntity persona; // Cambio a PersonaEntity en lugar de PersonaDTO
    private String comments;
    private String branch;
}
