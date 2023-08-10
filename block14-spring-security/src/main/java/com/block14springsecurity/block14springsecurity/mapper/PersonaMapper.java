package com.block14springsecurity.block14springsecurity.mapper;



import com.block14springsecurity.block14springsecurity.dto.input.PersonaInputDto;
import com.block14springsecurity.block14springsecurity.entities.PersonaEntity;

import org.springframework.stereotype.Component;


@Component
public class PersonaMapper {

    public PersonaEntity toEntity(PersonaInputDto personaInputDto) {
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setUsuario(personaInputDto.getUsuario());
        personaEntity.setPassword(personaInputDto.getPassword());
        personaEntity.setRole(personaInputDto.getRole());
        personaEntity.setName(personaInputDto.getName());
        personaEntity.setSurname(personaInputDto.getSurname());
        personaEntity.setCompanyEmail(personaInputDto.getCompanyEmail());
        personaEntity.setPersonalEmail(personaInputDto.getPersonalEmail());
        personaEntity.setCity(personaInputDto.getCity());
        personaEntity.setActive(personaInputDto.isActive());
        personaEntity.setCreatedDate(personaInputDto.getCreated_date());
        personaEntity.setImageUrl(personaInputDto.getImagenUrl());
        personaEntity.setTerminationDate(personaInputDto.getTermination_date());
        return personaEntity;

    }

    public PersonaInputDto toDTO(PersonaEntity personaEntity) {
        PersonaInputDto personaInputDto = new PersonaInputDto();
        personaInputDto.setId(personaEntity.getIdPersona());
        personaInputDto.setUsuario(personaEntity.getUsuario());
        personaInputDto.setPassword(personaEntity.getPassword());
        personaInputDto.setRole(personaEntity.getRole());
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
}
