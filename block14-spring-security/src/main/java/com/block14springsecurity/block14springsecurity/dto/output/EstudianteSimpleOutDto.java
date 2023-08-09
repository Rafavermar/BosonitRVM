package com.block14springsecurity.block14springsecurity.dto.output;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteSimpleOutDto {
    private Integer idStudent;
    private int numHoursWeek;
    private String comments;
}