package com.block14springsecurity.block14springsecurity.security.response;

import lombok.Data;

import java.util.List;


/**
 * Representa la respuesta que contiene el token JWT y otros detalles relacionados con el usuario autenticado.
 * Esta clase se utiliza para enviar información del usuario autenticado junto con el token JWT al cliente.
 *
 * <p>Esta respuesta incluye detalles como el tipo de token (por lo general, "Bearer"), el ID del usuario,
 * el nombre de usuario, el rol principal del usuario y una lista de todos los roles asociados con el usuario.</p>
 *
 * @author Tu nombre aquí
 */
@Data
public class JwtResponseDto {

    /**
     * El token JWT que representa la sesión autenticada del usuario.
     */
    private String token;

    /**
     * Tipo de token. En este caso, es "Bearer", que es un tipo común para tokens JWT.
     */
    private String type = "Bearer";

    /**
     * ID único del usuario.
     */
    private Integer id;

    /**
     * Nombre de usuario.
     */
    private String username;


    /**
     * Rol principal del usuario.
     */
    private String role;

    /**
     * Lista de todos los roles asociados con el usuario.
     */
    private List<String> roles;

    /**
     * Constructor para crear una instancia de {@link JwtResponseDto} con detalles proporcionados.
     *
     * @param accessToken Token JWT.
     * @param id          ID del usuario.
     * @param username    Nombre de usuario.
     * @param role        Rol principal del usuario.
     * @param roles       Lista de roles del usuario.
     */
    public JwtResponseDto( String accessToken, Integer id, String username, String role, List<String> roles ) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.role = role;
        this.roles = roles;
    }

}