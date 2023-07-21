package com.block7crudvalidation.block7crudvalidation.Mapper;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.PersonaDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Input.ProfesorDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfesorMapper {

    public ProfesorEntity toEntity(ProfesorDTO profesorDTO) {
        ProfesorEntity profesorEntity = new ProfesorEntity();
        profesorEntity.setIdProfesor(profesorDTO.getIdProfesor());
        profesorEntity.setPersona(convertToEntity(profesorDTO.getPersonaDTO()));
        profesorEntity.setComments(profesorDTO.getComments());
        profesorEntity.setBranch(profesorDTO.getBranch());
        return profesorEntity;
    }

    public ProfesorDTO toDTO(ProfesorEntity profesorEntity) {
        ProfesorDTO profesorDTO = new ProfesorDTO();
        profesorDTO.setIdProfesor(profesorEntity.getIdProfesor());
        profesorDTO.setPersonaDTO(convertToDTO(profesorEntity.getPersona()));
        profesorDTO.setComments(profesorEntity.getComments());
        profesorDTO.setBranch(profesorEntity.getBranch());
        return profesorDTO;
    }
    public List<ProfesorDTO> toDTOList(List<ProfesorEntity> profesorEntities) {
        List<ProfesorDTO> profesorDTOs = new ArrayList<>();
        for (ProfesorEntity profesorEntity : profesorEntities) {
            profesorDTOs.add(toDTO(profesorEntity));
        }
        return profesorDTOs;
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
}
