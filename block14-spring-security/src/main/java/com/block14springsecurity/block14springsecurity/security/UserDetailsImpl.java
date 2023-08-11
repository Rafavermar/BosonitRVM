package com.block14springsecurity.block14springsecurity.security;

import com.block14springsecurity.block14springsecurity.entities.PersonaEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;



/**
 * Implementación personalizada de la interfaz {@link UserDetails} proporcionada por Spring Security.
 * Esta clase representa un usuario del sistema con detalles específicos necesarios para el proceso de
 * autenticación y autorización.
 *
 * <p>El objetivo principal de esta implementación es adaptar una entidad de usuario
 * a la estructura que Spring Security espera para llevar a cabo sus operaciones de seguridad.</p>
 *
 *
 * @see UserDetails
 */

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String role;

    private String email;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;


    /**
     * Constructor principal para instanciar un objeto UserDetailsImpl.
     *
     * @param id El ID del usuario.
     * @param username El nombre de usuario.
     * @param email La dirección de correo electrónico del usuario.
     * @param role El rol del usuario.
     * @param password La contraseña del usuario.
     * @param authorities Colección de autoridades otorgadas al usuario.
     */
    public UserDetailsImpl( Integer id, String username, String email, String role, String password,
                            Collection<? extends GrantedAuthority> authorities ) {
        this.id = id;
        this.username = username;
        this.email=email;
        this.role = role;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Crea y retorna una instancia de UserDetailsImpl a partir de una entidad de persona.
     *
     * @param user La entidad de persona que se convertirá en un objeto UserDetailsImpl.
     * @return Una instancia de UserDetailsImpl.
     */

    public static UserDetailsImpl build( PersonaEntity user ) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));

        return new UserDetailsImpl(
                user.getIdPersona(),
                user.getName(),
                user.getCompanyEmail(),
                user.getRole(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals( Object o ) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
