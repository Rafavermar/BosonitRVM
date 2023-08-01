package com.jpacascade.jpacascade.mappers;

import com.jpacascade.jpacascade.Entities.CabeceraFra;
import com.jpacascade.jpacascade.Entities.LineasFra;
import com.jpacascade.jpacascade.dtos.output.FacturaOutputDto;
import com.jpacascade.jpacascade.dtos.output.LineaOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacturaMapper {
    @Mapping(source = "cliCodi.id", target = "clienteOutputDto.id")
    @Mapping(source = "cliCodi.nombre", target = "clienteOutputDto.nombre")
    FacturaOutputDto toDto(CabeceraFra cabeceraFra);

    LineaOutputDto toDto(LineasFra lineasFra);

    List<FacturaOutputDto> toDtos(List<CabeceraFra> cabeceras);
}
