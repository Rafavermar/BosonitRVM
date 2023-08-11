package com.block14springsecurity.block14springsecurity.security;

import com.block14springsecurity.block14springsecurity.entities.PersonaEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Representa un usuario dentro del sistema, implementando la interfaz {@link UserDetails}
 * que es estándar dentro de Spring Security para representar detalles de usuarios.
 *
 * <p>Esta clase actúa como una adaptación entre una entidad de persona (`PersonaEntity`) y
 * el contrato `UserDetails` requerido por Spring Security. Proporciona información relacionada
 * con la autenticación y autorización del usuario.</p>
 *
 *
 * @see UserDetails
 */


public class User implements UserDetails {

    /**
     * Entidad que representa una persona en el sistema.
     */
    private PersonaEntity personaEntity;

    /**
     * Lista de autoridades otorgadas al usuario.
     */
    private List<GrantedAuthority> authorities;

    /**
     * Constructor para crear una instancia de {@link User} con la entidad de persona
     * y una lista de autoridades otorgadas.
     *
     * @param persona Entidad de persona que representa detalles del usuario.
     * @param authorities Lista de autoridades otorgadas al usuario.
     */

    public User( PersonaEntity persona, List<GrantedAuthority> authorities ) {
        this.personaEntity = persona;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return personaEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return personaEntity.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
