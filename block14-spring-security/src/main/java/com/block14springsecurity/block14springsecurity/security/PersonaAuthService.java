package com.block14springsecurity.block14springsecurity.security;


import com.block14springsecurity.block14springsecurity.mapper.PersonaMapper;
import com.block14springsecurity.block14springsecurity.repository.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Servicio que gestiona las operaciones relacionadas con la autenticación de personas.
 * Proporciona métodos y utilidades necesarios para interactuar con el repositorio de personas,
 * codificar contraseñas y convertir entre entidades y DTOs de personas.
 *
 * <p>Este servicio utiliza {@link PersonaRepository} para acceder y manipular la base de datos de personas,
 * {@link PasswordEncoder} para codificar y verificar contraseñas, y {@link PersonaMapper} para convertir
 * entre entidades y DTOs relacionados con personas.</p>
 *

 */

@Service
public class PersonaAuthService {

    /**
     * Repositorio utilizado para interactuar con la base de datos de personas.
     */

    private final PersonaRepository personaRepository;

    /**
     * Codificador de contraseñas utilizado para codificar contraseñas antes de guardarlas
     * y para verificar contraseñas durante la autenticación.
     */
    private final PasswordEncoder encoder;

    /**
     * Mapper utilizado para convertir entre entidades y DTOs relacionados con personas.
     */
    private final PersonaMapper personaMapper;

    /**
     * Constructor para inicializar {@link PersonaAuthService} con las dependencias requeridas.
     *
     * @param personaRepository Repositorio de personas para interactuar con la base de datos.
     * @param encoder           Codificador de contraseñas para las operaciones de codificación y verificación.
     * @param personaMapper     Mapper de personas para convertir entre entidades y DTOs.
     */

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
