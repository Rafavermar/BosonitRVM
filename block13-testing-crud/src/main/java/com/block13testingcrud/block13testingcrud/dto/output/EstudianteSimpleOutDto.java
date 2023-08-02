package com.block13testingcrud.block13testingcrud.dto.output;


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