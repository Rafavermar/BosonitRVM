package com.block12criteriabuilder.block12criteriabuilder.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDto {
    private Integer idStudent;
    private Integer idPersona;;
    private int numHoursWeek;
    private String comments;
    private Integer idProfesor;
    private String branch;
}
