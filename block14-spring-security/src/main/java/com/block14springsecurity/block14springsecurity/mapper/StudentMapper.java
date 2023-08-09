package com.block14springsecurity.block14springsecurity.mapper;


import com.block14springsecurity.block14springsecurity.dto.input.PersonaInputDto;
import com.block14springsecurity.block14springsecurity.dto.input.ProfesorInputDto;
import com.block14springsecurity.block14springsecurity.dto.input.StudentInputDto;
import com.block14springsecurity.block14springsecurity.entities.PersonaEntity;
import com.block14springsecurity.block14springsecurity.entities.ProfesorEntity;
import com.block14springsecurity.block14springsecurity.entities.StudentEntity;
import com.block14springsecurity.block14springsecurity.exception.EntityNotFoundException;
import com.block14springsecurity.block14springsecurity.repository.ProfesorRepository;
import com.block14springsecurity.block14springsecurity.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    @Autowired
    public PersonaService personaService;
    @Autowired
    public ProfesorRepository profesorRepository;

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

    public PersonaInputDto convertToDTO(PersonaEntity personaEntity) {
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

    public ProfesorInputDto convertToDTO(ProfesorEntity profesorEntity) {
        ProfesorInputDto profesorInputDto = new ProfesorInputDto();
        if (profesorEntity != null) {
            profesorInputDto.setIdProfesor(profesorEntity.getIdProfesor());
            profesorInputDto.setIdPersona(profesorEntity.getPersona().getIdPersona());
            profesorInputDto.setComments(profesorEntity.getComments());
            profesorInputDto.setBranch(profesorEntity.getBranch());
        }
        return profesorInputDto;
    }

    public List<PersonaInputDto> toDTOList(List<PersonaEntity> personaEntities) {
        return personaEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
