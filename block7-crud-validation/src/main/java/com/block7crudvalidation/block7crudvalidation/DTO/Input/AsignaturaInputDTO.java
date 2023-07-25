package com.block7crudvalidation.block7crudvalidation.DTO.Input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaInputDTO {

    private Integer idAsignatura;
    private String coments;
    private LocalDate initialDate;
    private LocalDate finishDate;
    private Integer idStudent;
    private Integer idProfesor;

}
