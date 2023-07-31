package com.block12criteriabuilder.block12criteriabuilder.mapper;


import com.block12criteriabuilder.block12criteriabuilder.dto.input.PersonaInputDto;
import com.block12criteriabuilder.block12criteriabuilder.dto.input.ProfesorInputDto;
import com.block12criteriabuilder.block12criteriabuilder.dto.input.StudentInputDto;
import com.block12criteriabuilder.block12criteriabuilder.entities.PersonaEntity;
import com.block12criteriabuilder.block12criteriabuilder.entities.ProfesorEntity;
import com.block12criteriabuilder.block12criteriabuilder.entities.StudentEntity;
import com.block12criteriabuilder.block12criteriabuilder.exception.EntityNotFoundException;

import com.block12criteriabuilder.block12criteriabuilder.repository.ProfesorRepository;
import com.block12criteriabuilder.block12criteriabuilder.services.PersonaService;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class StudentMapper {

    @Autowired
    private PersonaService personaService; // Asegúrate de que PersonaService esté correctamente inyectado.
    @Autowired
    private ProfesorRepository profesorRepository;

    public StudentEntity toEntity(StudentInputDto studentInputDto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setIdStudent(studentInputDto.getIdStudent());

        // Buscar la entidad PersonaEntity por su ID
        PersonaEntity personaEntity = personaService.buscarPorId(studentInputDto.getIdPersona());
        studentEntity.setPersona(personaEntity);

        studentEntity.setNumHoursWeek(studentInputDto.getNumHoursWeek());
        studentEntity.setComments(studentInputDto.getComments());

        // Buscar la entidad ProfesorEntity por su ID
        if (studentInputDto.getIdProfesor() != null) {
            ProfesorEntity profesorEntity = profesorRepository.findById(studentInputDto.getIdProfesor())
                    .orElseThrow(() -> new EntityNotFoundException("Profesor with id " + studentInputDto.getIdProfesor() + " not found"));
            studentEntity.setProfesor(profesorEntity);
        }

        studentEntity.setBranch(studentInputDto.getBranch());

        return studentEntity;
    }

    public StudentInputDto toDTO(StudentEntity studentEntity) {
        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setIdStudent(studentEntity.getIdStudent());
        studentInputDto.setIdPersona(studentEntity.getPersona().getIdPersona());
        studentInputDto.setNumHoursWeek(studentEntity.getNumHoursWeek());
        studentInputDto.setComments(studentEntity.getComments());

        // Mapear el ID del profesor a StudentDTO
        if (studentEntity.getProfesor() != null) {
            studentInputDto.setIdProfesor(studentEntity.getProfesor().getIdProfesor());
        }

        studentInputDto.setBranch(studentEntity.getBranch());

        return studentInputDto;
    }

    private PersonaInputDto convertToDTO(PersonaEntity personaEntity) {
        PersonaInputDto personaInputDto = new PersonaInputDto();
        personaInputDto.setId(personaEntity.getIdPersona());
        personaInputDto.setUsuario(personaEntity.getUsuario());
        personaInputDto.setPassword(personaEntity.getPassword());
        personaInputDto.setName(personaEntity.getName());
        personaInputDto.setSurname(personaEntity.getSurname());
        personaInputDto.setCompanyEmail(personaEntity.getCompanyEmail());
        personaInputDto.setPersonalEmail(personaEntity.getPersonalEmail());
        personaInputDto.setCity(personaEntity.getCity());
        personaInputDto.setActive(personaEntity.isActive());
        personaInputDto.setCreated_date(personaEntity.getCreatedDate());
        personaInputDto.setImagenUrl(personaEntity.getImageUrl());
        personaInputDto.setTermination_date(personaEntity.getTerminationDate());

        return personaInputDto;
    }

    private ProfesorInputDto convertToDTO(ProfesorEntity profesorEntity) {
        ProfesorInputDto profesorInputDto = new ProfesorInputDto();
        if (profesorEntity != null) {
            profesorInputDto.setIdProfesor(profesorEntity.getIdProfesor());
            profesorInputDto.setIdPersona(profesorEntity.getPersona().getIdPersona());
            profesorInputDto.setComments(profesorEntity.getComments());
            profesorInputDto.setBranch(profesorEntity.getBranch());
        }
        return profesorInputDto;
    }
}
