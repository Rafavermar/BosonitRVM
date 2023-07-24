package com.block7crudvalidation.block7crudvalidation.Mapper;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.PersonaDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Input.ProfesorDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Output.ProfesorFullDTO;
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

        // Establecer PersonaEntity con solo el ID establecido
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(profesorDTO.getIdPersona());
        profesorEntity.setPersona(personaEntity);

        profesorEntity.setComments(profesorDTO.getComments());
        profesorEntity.setBranch(profesorDTO.getBranch());
        return profesorEntity;
    }

    public ProfesorDTO toDTO(ProfesorEntity profesorEntity) {
        ProfesorDTO profesorDTO = new ProfesorDTO();
        profesorDTO.setIdProfesor(profesorEntity.getIdProfesor());

        // Establecer el idPersona de la entidad PersonaEntity asociada al profesor
        if (profesorEntity.getPersona() != null) {
            profesorDTO.setIdPersona(profesorEntity.getPersona().getIdPersona());
        }

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


    public ProfesorFullDTO toFullDTO(ProfesorEntity profesorEntity) {
        ProfesorFullDTO profesorFullDTO = new ProfesorFullDTO();
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

    public List<ProfesorFullDTO> toFullDTOList(List<ProfesorEntity> profesorEntities) {
        List<ProfesorFullDTO> profesorFullDTOs = new ArrayList<>();
        for (ProfesorEntity profesorEntity : profesorEntities) {
            profesorFullDTOs.add(toFullDTO(profesorEntity));
        }
        return profesorFullDTOs;
    }

}
