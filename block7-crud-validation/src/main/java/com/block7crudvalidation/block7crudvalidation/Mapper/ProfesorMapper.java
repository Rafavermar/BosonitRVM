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
}
