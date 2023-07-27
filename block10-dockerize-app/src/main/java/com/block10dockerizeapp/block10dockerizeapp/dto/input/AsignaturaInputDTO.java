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
public class AsignaturaInputDTO {

    private Integer idAsignatura;
    private String coments;
    private Date initialDate;
    private Date finishDate;

}

