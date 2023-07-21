package com.block7crudvalidation.block7crudvalidation.Mapper;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.PersonaDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Input.ProfesorDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Input.StudentDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import com.block7crudvalidation.block7crudvalidation.Services.PersonaService;
import org.springframework.stereotype.Component;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    @Autowired
    private PersonaService personaService; // Asegúrate de que PersonaService esté correctamente inyectado.

    public StudentEntity toEntity(StudentDTO studentDTO) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setIdStudent(studentDTO.getIdStudent());

        // Buscar la entidad PersonaEntity por su ID
        PersonaEntity personaEntity = personaService.buscarPorId(studentDTO.getIdPersona());
        studentEntity.setPersona(personaEntity);

        studentEntity.setNumHoursWeek(studentDTO.getNumHoursWeek());
        studentEntity.setComments(studentDTO.getComments());
        studentEntity.setBranch(studentDTO.getBranch());

        return studentEntity;
    }

    public StudentDTO toDTO(StudentEntity studentEntity) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setIdStudent(studentEntity.getIdStudent());
        studentDTO.setIdPersona(studentEntity.getPersona().getIdPersona());
        studentDTO.setNumHoursWeek(studentEntity.getNumHoursWeek());
        studentDTO.setComments(studentEntity.getComments());
        studentDTO.setProfesorDTO(convertToDTO(studentEntity.getProfesor()));
        studentDTO.setBranch(studentEntity.getBranch());

        return studentDTO;
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
        if (profesorEntity != null) {
            profesorDTO.setIdProfesor(profesorEntity.getIdProfesor());
            profesorDTO.setIdPersona(profesorEntity.getPersona().getIdPersona());
            profesorDTO.setComments(profesorEntity.getComments());
            profesorDTO.setBranch(profesorEntity.getBranch());
        }
        return profesorDTO;
    }
}
