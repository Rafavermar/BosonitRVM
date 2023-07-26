package com.block7crudvalidation.block7crudvalidation.mapper;

import com.block7crudvalidation.block7crudvalidation.dto.input.ProfesorInputDto;
import com.block7crudvalidation.block7crudvalidation.dto.output.ProfesorFullOutputDto;
import com.block7crudvalidation.block7crudvalidation.entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.entities.ProfesorEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfesorMapper {

    public ProfesorEntity toEntity(ProfesorInputDto profesorInputDto) {
        ProfesorEntity profesorEntity = new ProfesorEntity();
        profesorEntity.setIdProfesor(profesorInputDto.getIdProfesor());

        // Establecer PersonaEntity con solo el ID establecido
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(profesorInputDto.getIdPersona());
        profesorEntity.setPersona(personaEntity);

        profesorEntity.setComments(profesorInputDto.getComments());
        profesorEntity.setBranch(profesorInputDto.getBranch());
        return profesorEntity;
    }

    public ProfesorInputDto toDTO(ProfesorEntity profesorEntity) {
        ProfesorInputDto profesorInputDto = new ProfesorInputDto();
        profesorInputDto.setIdProfesor(profesorEntity.getIdProfesor());

        // Establecer el idPersona de la entidad PersonaEntity asociada al profesor
        if (profesorEntity.getPersona() != null) {
            profesorInputDto.setIdPersona(profesorEntity.getPersona().getIdPersona());
        }

        profesorInputDto.setComments(profesorEntity.getComments());
        profesorInputDto.setBranch(profesorEntity.getBranch());
        return profesorInputDto;
    }

    public List<ProfesorInputDto> toDTOList(List<ProfesorEntity> profesorEntities) {
        List<ProfesorInputDto> profesorInputDtos = new ArrayList<>();
        for (ProfesorEntity profesorEntity : profesorEntities) {
            profesorInputDtos.add(toDTO(profesorEntity));
        }
        return profesorInputDtos;
    }


    public ProfesorFullOutputDto toFullDTO(ProfesorEntity profesorEntity) {
        ProfesorFullOutputDto profesorFullDTO = new ProfesorFullOutputDto();
        profesorFullDTO.setIdProfesor(profesorEntity.getIdProfesor());

        // Establecer el idPersona y otros detalles de la entidad PersonaEntity asociada al profesor
        if (profesorEntity.getPersona() != null) {
            profesorFullDTO.setIdPersona(profesorEntity.getPersona().getIdPersona());
            profesorFullDTO.setUsuario(profesorEntity.getPersona().getUsuario());
            profesorFullDTO.setName(profesorEntity.getPersona().getName());
            profesorFullDTO.setSurname(profesorEntity.getPersona().getSurname());
            profesorFullDTO.setCompanyEmail(profesorEntity.getPersona().getCompanyEmail());
            profesorFullDTO.setPersonalEmail(profesorEntity.getPersona().getPersonalEmail());
            profesorFullDTO.setCity(profesorEntity.getPersona().getCity());
            profesorFullDTO.setActive(profesorEntity.getPersona().isActive());
            profesorFullDTO.setCreatedDate(profesorEntity.getPersona().getCreatedDate());
            profesorFullDTO.setImageUrl(profesorEntity.getPersona().getImageUrl());
            profesorFullDTO.setTerminationDate(profesorEntity.getPersona().getTerminationDate());
        }

        profesorFullDTO.setComments(profesorEntity.getComments());
        profesorFullDTO.setBranch(profesorEntity.getBranch());
        return profesorFullDTO;
    }

    public List<ProfesorFullOutputDto> toFullDTOList(List<ProfesorEntity> profesorEntities) {
        List<ProfesorFullOutputDto> profesorFullDTOs = new ArrayList<>();
        for (ProfesorEntity profesorEntity : profesorEntities) {
            profesorFullDTOs.add(toFullDTO(profesorEntity));
        }
        return profesorFullDTOs;
    }

}
