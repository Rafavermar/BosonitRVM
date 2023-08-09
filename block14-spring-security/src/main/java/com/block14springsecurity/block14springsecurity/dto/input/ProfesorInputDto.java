package com.block14springsecurity.block14springsecurity.dto.input;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorInputDto {
    private Integer idProfesor;
    private Integer idPersona;;
    private String comments;
    private String branch;
}
