package com.block14springsecurity.block14springsecurity.security;


import org.springframework.security.core.GrantedAuthority;

public class SimpleGrantedAuthority implements GrantedAuthority {
    private String role;

    public SimpleGrantedAuthority( String role ) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}

