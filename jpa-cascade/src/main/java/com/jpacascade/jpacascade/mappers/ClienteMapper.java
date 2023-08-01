package com.jpacascade.jpacascade.mappers;

import com.jpacascade.jpacascade.Entities.Cliente;
import com.jpacascade.jpacascade.dtos.output.ClienteOutputDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteOutputDto toDto(Cliente cliente); // Mapeo para Cliente
    List<ClienteOutputDto> toDtos(List<Cliente> clientes);
}
