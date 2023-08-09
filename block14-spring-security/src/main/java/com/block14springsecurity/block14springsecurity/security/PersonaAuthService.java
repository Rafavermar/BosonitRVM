package com.block14springsecurity.block14springsecurity.security;


import com.block14springsecurity.block14springsecurity.mapper.PersonaMapper;
import com.block14springsecurity.block14springsecurity.repository.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PersonaAuthService {

    private final PersonaRepository personaRepository;
    private final PasswordEncoder encoder;
    private final PersonaMapper personaMapper;

    @Autowired
    public PersonaAuthService( PersonaRepository personaRepository, PasswordEncoder encoder,PersonaMapper personaMapper ) {
        this.personaRepository = personaRepository;
        this.encoder = encoder;
        this.personaMapper = personaMapper;
    }
/**
    @Transactional
    public PersonaInputDto registerPersona(SignupPersonaDto signupPersonaDto ) {
        if (personaRepository.existsByName(SignupPersonaDto.getUsername())) {
            throw new IllegalArgumentException("Error: Username is already taken!");
        }

        // Create new passenger's account
        var persona = new PersonaEntity(
                signupPersonaDto.getUsername(),
                encoder.encode(signupPersonaDto.getPassword()),
                "USER"); // Assuming all registered passengers are USERS

        persona = personaRepository.save(persona);

        // Mapping back the created passenger to SignupPassengerDto
        return personaMapper.toDTO(persona);
    }

    **/
}
