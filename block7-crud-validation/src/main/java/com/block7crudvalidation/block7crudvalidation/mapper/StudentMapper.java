package com.block7crudvalidation.block7crudvalidation.mapper;

import com.block7crudvalidation.block7crudvalidation.dto.input.PersonaDTO;
import com.block7crudvalidation.block7crudvalidation.dto.input.ProfesorDTO;
import com.block7crudvalidation.block7crudvalidation.dto.input.StudentDTO;
import com.block7crudvalidation.block7crudvalidation.entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.entities.ProfesorEntity;
import com.block7crudvalidation.block7crudvalidation.entities.StudentEntity;
import com.block7crudvalidation.block7crudvalidation.exception.EntityNotFoundException;
import com.block7crudvalidation.block7crudvalidation.repository.ProfesorRepository;
import com.block7crudvalidation.block7crudvalidation.services.PersonaService;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class StudentMapper {

    @Autowired
    private PersonaService personaService; // Asegúrate de que PersonaService esté correctamente inyectado.
    @Autowired
    private ProfesorRepository profesorRepository;

    public StudentEntity toEntity(StudentDTO studentDTO) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setIdStudent(studentDTO.getIdStudent());

        // Buscar la entidad PersonaEntity por su ID
        PersonaEntity personaEntity = personaService.buscarPorId(studentDTO.getIdPersona());
        studentEntity.setPersona(personaEntity);

        studentEntity.setNumHoursWeek(studentDTO.getNumHoursWeek());
        studentEntity.setComments(studentDTO.getComments());

        // Buscar la entidad ProfesorEntity por su ID
        if (studentDTO.getIdProfesor() != null) {
            ProfesorEntity profesorEntity = profesorRepository.findById(studentDTO.getIdProfesor())
                    .orElseThrow(() -> new EntityNotFoundException("Profesor with id " + studentDTO.getIdProfesor() + " not found"));
            studentEntity.setProfesor(profesorEntity);
        }

        studentEntity.setBranch(studentDTO.getBranch());

        return studentEntity;
    }

    public StudentDTO toDTO(StudentEntity studentEntity) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setIdStudent(studentEntity.getIdStudent());
        studentDTO.setIdPersona(studentEntity.getPersona().getIdPersona());
        studentDTO.setNumHoursWeek(studentEntity.getNumHoursWeek());
        studentDTO.setComments(studentEntity.getComments());

        // Mapear el ID del profesor a StudentDTO
        if (studentEntity.getProfesor() != null) {
            studentDTO.setIdProfesor(studentEntity.getProfesor().getIdProfesor());
        }

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
