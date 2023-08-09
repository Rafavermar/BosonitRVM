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
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonaRepository personaRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

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

