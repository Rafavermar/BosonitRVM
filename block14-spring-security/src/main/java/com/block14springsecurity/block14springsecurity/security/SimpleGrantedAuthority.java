package com.block14springsecurity.block14springsecurity.security;


import org.springframework.security.core.GrantedAuthority;

/**
 * Representa una autoridad simple que se otorga a los usuarios de seguridad.
 *
 * <p>Esta implementación se utiliza para representar roles o privilegios
 * asignados a un usuario en el contexto de seguridad. El propósito principal
 * es facilitar la asignación de autorizaciones a los usuarios a través de roles.</p>
 *
 * <p>La clase implementa la interfaz {@link GrantedAuthority}, lo que la hace
 * adecuada para usar con Spring Security y otras librerías de seguridad que esperan
 * esta forma de representación de autoridad.</p>
 *
 * @author Tu nombre aquí
 * @see GrantedAuthority
 */

public class SimpleGrantedAuthority implements GrantedAuthority {

    /**
     * El rol o autoridad otorgada.
     */
    private String role;

    /**
     * Constructor para crear una instancia de {@link SimpleGrantedAuthority}
     * con el rol o autoridad especificada.
     *
     * @param role El rol o autoridad que se otorgará.
     */

    public SimpleGrantedAuthority( String role ) {
        this.role = role;
    }

    /**
     * Obtiene el rol o autoridad otorgada.
     *
     * @return El rol o autoridad.
     */

    @Override
    public String getAuthority() {
        return role;
    }
}

