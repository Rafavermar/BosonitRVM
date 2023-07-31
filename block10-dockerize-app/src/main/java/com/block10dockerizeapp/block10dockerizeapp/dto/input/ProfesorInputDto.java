package com.block10dockerizeapp.block10dockerizeapp.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorInputDto {
    private Integer idProfesor;
    private Integer idPersona;;
    private String comments;
    private String branch;
}