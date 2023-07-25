package com.block7crudvalidation.block7crudvalidation.Mapper;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.AsignaturaInputDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.AsignaturaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AsignaturaMapper {

    public AsignaturaEntity toEntity(AsignaturaInputDTO asignaturaDTO, StudentEntity studentEntity) {
        AsignaturaEntity asignaturaEntity = new AsignaturaEntity();
        asignaturaEntity.setComents(asignaturaDTO.getComents());
        asignaturaEntity.setInitialDate(asignaturaDTO.getInitialDate());
        asignaturaEntity.setFinishDate(asignaturaDTO.getFinishDate());
        asignaturaEntity.setStudent(studentEntity);
        return asignaturaEntity;
    }

    public AsignaturaInputDTO toDTO(AsignaturaEntity asignaturaEntity) {
        AsignaturaInputDTO asignaturaInputDTO = new AsignaturaInputDTO();
        asignaturaInputDTO.setIdAsignatura(asignaturaEntity.getIdAsignatura());
        asignaturaInputDTO.setComents(asignaturaEntity.getComents());
        asignaturaInputDTO.setInitialDate(asignaturaEntity.getInitialDate());
        asignaturaInputDTO.setFinishDate(asignaturaEntity.getFinishDate());
        asignaturaInputDTO.setIdStudent(asignaturaEntity.getStudent().getIdStudent());
        return asignaturaInputDTO;
    }

    // Método para convertir una lista de entidades a una lista de DTOs
    public List<AsignaturaInputDTO> toDTOList(List<AsignaturaEntity> asignaturaEntities) {
        return asignaturaEntities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

