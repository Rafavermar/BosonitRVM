package com.block10dockerizeapp.block10dockerizeapp.mapper;


import com.block10dockerizeapp.block10dockerizeapp.dto.input.PersonaInputDto;
import com.block10dockerizeapp.block10dockerizeapp.dto.input.ProfesorInputDto;
import com.block10dockerizeapp.block10dockerizeapp.dto.input.StudentInputDto;
import com.block10dockerizeapp.block10dockerizeapp.entities.PersonaEntity;
import com.block10dockerizeapp.block10dockerizeapp.entities.ProfesorEntity;
import com.block10dockerizeapp.block10dockerizeapp.entities.StudentEntity;
import com.block10dockerizeapp.block10dockerizeapp.exception.EntityNotFoundException;
import com.block10dockerizeapp.block10dockerizeapp.repository.ProfesorRepository;
import com.block10dockerizeapp.block10dockerizeapp.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
