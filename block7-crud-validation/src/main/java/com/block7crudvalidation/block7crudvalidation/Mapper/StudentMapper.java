package com.block7crudvalidation.block7crudvalidation.Mapper;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.PersonaDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Input.ProfesorDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Input.StudentDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StudentMapper {

    public StudentEntity toEntity(StudentDTO studentDTO) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setIdStudent(studentDTO.getIdStudent());
        studentEntity.setPersona(convertToEntity(studentDTO.getPersonaDTO())); // Llamada al método convertToEntity
        studentEntity.setNumHoursWeek(studentDTO.getNumHoursWeek());
        studentEntity.setComments(studentDTO.getComments());
        studentEntity.setBranch(studentDTO.getBranch());

        return studentEntity;
    }

    public StudentDTO toDTO(StudentEntity studentEntity) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setIdStudent(studentEntity.getIdStudent());
        studentDTO.setPersonaDTO(convertToDTO(studentEntity.getPersona())); // Llamada al método convertToDTO
        studentDTO.setNumHoursWeek(studentEntity.getNumHoursWeek());
        studentDTO.setComments(studentEntity.getComments());
        studentDTO.setProfesorDTO(convertToDTO(studentEntity.getProfesor())); // Llamada al método convertToDTO
        studentDTO.setBranch(studentEntity.getBranch());

        return studentDTO;
    }

    private PersonaEntity convertToEntity(PersonaDTO personaDTO) {
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(personaDTO.getId());
        personaEntity.setUsuario(personaDTO.getUsuario());
        personaEntity.setPassword(personaDTO.getPassword());
        personaEntity.setName(personaDTO.getName());
        personaEntity.setSurname(personaDTO.getSurname());
        personaEntity.setCompanyEmail(personaDTO.getCompany_email());
        personaEntity.setPersonalEmail(personaDTO.getPersonal_email());
        personaEntity.setCity(personaDTO.getCity());
        personaEntity.setActive(personaDTO.isActive());
        personaEntity.setCreatedDate(personaDTO.getCreated_date());
        personaEntity.setImageUrl(personaDTO.getImagen_url());
        personaEntity.setTerminationDate(personaDTO.getTermination_date());

        return personaEntity;
    }

    private PersonaDTO convertToDTO(PersonaEntity personaEntity) {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(personaEntity.getIdPersona());
        personaDTO.setUsuario(personaEntity.getUsuario());
        personaDTO.setPassword(personaEntity.getPassword());
        personaDTO.setName(personaEntity.getName());
        personaDTO.setSurname(personaEntity.getSurname());
        personaDTO.setCompany_email(personaEntity.getCompanyEmail());
        personaDTO.setPersonal_email(personaEntity.getPersonalEmail());
        personaDTO.setCity(personaEntity.getCity());
        personaDTO.setActive(personaEntity.isActive());
        personaDTO.setCreated_date(personaEntity.getCreatedDate());
        personaDTO.setImagen_url(personaEntity.getImageUrl());
        personaDTO.setTermination_date(personaEntity.getTerminationDate());

        return personaDTO;
    }

    private ProfesorDTO convertToDTO(ProfesorEntity profesorEntity) {
        ProfesorDTO profesorDTO = new ProfesorDTO();
        // Implementar el mapeo de ProfesorEntity a ProfesorDTO
        return profesorDTO;
    }
}
