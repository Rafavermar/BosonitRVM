package com.block14springsecurity.block14springsecurity.security;

import com.block14springsecurity.block14springsecurity.entities.PersonaEntity;
import com.block14springsecurity.block14springsecurity.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementación personalizada del servicio {@link UserDetailsService} proporcionado por Spring Security.
 *
 * <p>Esta implementación se encarga de cargar los detalles de un usuario a partir de su nombre de usuario.
 * Es utilizado durante el proceso de autenticación para recuperar detalles del usuario desde la base de datos
 * y construir un objeto UserDetails que Spring Security puede utilizar para llevar a cabo las operaciones
 * de autenticación y autorización.</p>
 *
 *
 * @see UserDetailsService
 */

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonaRepository personaRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    /**
     * Carga un usuario a partir de su nombre de usuario y lo retorna como un objeto UserDetails.
     *
     * <p>Si el usuario no se encuentra en la base de datos, se lanza una excepción {@link UsernameNotFoundException}.</p>
     *
     * @param username El nombre de usuario del usuario a cargar.
     * @return Un objeto UserDetails representando al usuario cargado.
     * @throws UsernameNotFoundException Si el nombre de usuario no se encuentra en la base de datos.
     */

    @Override
    @Transactional
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        LOGGER.info("Loading user by username: {}", username);

        PersonaEntity personaEntity = (PersonaEntity) personaRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        LOGGER.info("User loaded: {}", personaEntity.getName());

        return UserDetailsImpl.build(personaEntity);
    }
}

