package com.jpacascade.jpacascade.mappers;

import com.jpacascade.jpacascade.entities.Cliente;
import com.jpacascade.jpacascade.dtos.output.ClienteOutputDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteOutputDto toDto(Cliente cliente);
    List<ClienteOutputDto> toDtos(List<Cliente> clientes);
}
