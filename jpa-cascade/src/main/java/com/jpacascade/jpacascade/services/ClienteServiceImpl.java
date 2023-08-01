package com.jpacascade.jpacascade.services;

import com.jpacascade.jpacascade.Entities.Cliente;
import com.jpacascade.jpacascade.dtos.output.ClienteOutputDto;
import com.jpacascade.jpacascade.mappers.ClienteMapper;
import com.jpacascade.jpacascade.mappers.FacturaMapper;
import com.jpacascade.jpacascade.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public List<ClienteOutputDto> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDtos(clientes);
    }
}

