package com.block14springsecurity.block14springsecurity.mapper;



import com.block14springsecurity.block14springsecurity.dto.input.AsignaturaInputDTO;
import com.block14springsecurity.block14springsecurity.entities.AsignaturaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AsignaturaMapper {

    public AsignaturaEntity toEntity(AsignaturaInputDTO asignaturaDTO) {
        AsignaturaEntity asignaturaEntity = new AsignaturaEntity();
        asignaturaEntity.setComents(asignaturaDTO.getComents());
        asignaturaEntity.setInitialDate(asignaturaDTO.getInitialDate());
        asignaturaEntity.setFinishDate(asignaturaDTO.getFinishDate());

        // Quitamos la vinculación al estudiante
        // asignaturaEntity.setStudent(studentEntity);

        return asignaturaEntity;
    }

    public AsignaturaInputDTO toDTO(AsignaturaEntity asignaturaEntity) {
        AsignaturaInputDTO asignaturaInputDTO = new AsignaturaInputDTO();
        asignaturaInputDTO.setIdAsignatura(asignaturaEntity.getIdAsignatura());
        asignaturaInputDTO.setComents(asignaturaEntity.getComents());
        asignaturaInputDTO.setInitialDate(asignaturaEntity.getInitialDate());
        asignaturaInputDTO.setFinishDate(asignaturaEntity.getFinishDate());


        return asignaturaInputDTO;
    }


    // Método para convertir una lista de entidades a una lista de DTOs
    public List<AsignaturaInputDTO> toDTOList(List<AsignaturaEntity> asignaturaEntities) {
        return asignaturaEntities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
