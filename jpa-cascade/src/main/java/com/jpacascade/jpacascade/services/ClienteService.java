package com.jpacascade.jpacascade.services;

import com.jpacascade.jpacascade.dtos.output.ClienteOutputDto;

import java.util.List;

public interface ClienteService {
    List<ClienteOutputDto> getAllClientes();
}
