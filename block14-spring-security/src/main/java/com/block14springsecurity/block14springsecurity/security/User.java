package com.block14springsecurity.block14springsecurity.security;

import com.block14springsecurity.block14springsecurity.entities.PersonaEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class User implements UserDetails {
    private PersonaEntity personaEntity;
    private List<GrantedAuthority> authorities;

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
