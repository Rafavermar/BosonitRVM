package com.jpacascade.jpacascade.mappers;

import com.jpacascade.jpacascade.entities.CabeceraFra;
import com.jpacascade.jpacascade.entities.LineasFra;
import com.jpacascade.jpacascade.dtos.output.FacturaOutputDto;
import com.jpacascade.jpacascade.dtos.output.LineaOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FacturaMapper {
    @Mapping(source = "cliCodi.id", target = "clienteOutputDto.id")
    @Mapping(source = "cliCodi.nombre", target = "clienteOutputDto.nombre")
    @Mapping(source = "lineas", target = "lineaOutputDtoList", qualifiedByName = "mapLineas")
    FacturaOutputDto toFacturaDto(CabeceraFra cabeceraFra);
    @Mapping(source = "proNomb", target = "producto")
    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(expression = "java(lineasFra.getCantidad() * lineasFra.getPrecio())", target = "importe")
    LineaOutputDto toLineaDto(LineasFra lineasFra);

    List<FacturaOutputDto> toFacturaDtos(List<CabeceraFra> cabeceras);

    @Named("mapLineas")
    default List<LineaOutputDto> mapLineas(List<LineasFra> lineas) {
        return lineas.stream().map(this::toLineaDto).collect(Collectors.toList());
    }
}
