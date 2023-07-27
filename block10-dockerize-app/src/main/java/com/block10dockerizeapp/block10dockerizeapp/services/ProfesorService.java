package com.block10dockerizeapp.block10dockerizeapp.services;


import com.block10dockerizeapp.block10dockerizeapp.dto.input.ProfesorInputDto;
import com.block10dockerizeapp.block10dockerizeapp.dto.output.ProfesorFullOutputDto;
import com.block10dockerizeapp.block10dockerizeapp.entities.ProfesorEntity;

import java.util.List;

public interface ProfesorService {

    ProfesorEntity saveProfesor(ProfesorEntity profesor);

    List<ProfesorEntity> getAllProfesores();

    ProfesorEntity getProfesorById(Integer id);

    void deleteProfesor(Integer id);

    ProfesorEntity updateProfesor(Integer id, ProfesorEntity profesor);

    ProfesorEntity updateProfesorEntity(Integer id, ProfesorEntity profesorEntity);

    // New methods
    ProfesorInputDto getProfesorDTOById(Integer id);

    List<ProfesorInputDto> getProfesoresDTOByName(String name);

    ProfesorFullOutputDto getProfesorFullDetailsById(Integer id);

    List<ProfesorFullOutputDto> getProfesorFullDetailsByName(String name);

    ProfesorInputDto createProfesor(ProfesorInputDto profesorInputDto);
    ProfesorInputDto updateProfesor(Integer id, ProfesorInputDto profesorInputDto);
}
