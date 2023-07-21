package com.block7crudvalidation.block7crudvalidation.Mapper;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.PersonaDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {

    public PersonaEntity toEntity(PersonaDTO personaDTO) {
        PersonaEntity personaEntity = new PersonaEntity();
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

    public PersonaDTO toDTO(PersonaEntity personaEntity) {
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
