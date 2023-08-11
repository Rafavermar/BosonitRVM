package com.block14springsecurity.block14springsecurity.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Implementa un punto de entrada de autenticación personalizado que se invoca cuando un usuario intenta
 * acceder a un recurso protegido sin proveer credenciales válidas.
 *
 * <p>Este punto de entrada maneja la respuesta que se envía como respuesta a la petición no autorizada,
 * enviando un error en formato JSON con detalles sobre el error.</p>
 *
 *
 */

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    /**
     * Logger para registrar eventos relacionados con la autenticación no autorizada.
     */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(AuthEntryPointJwt.class);

    /**
     * Es invocado cuando un usuario intenta acceder a un recurso protegido sin proveer credenciales válidas.
     *
     * <p>En este método, se establece la respuesta como no autorizada (401) y se proporciona
     * un mensaje de error detallado en formato JSON.</p>
     *
     * <p>Para serializar la respuesta en formato JSON se utiliza {@link ObjectMapper}. ObjectMapper es una herramienta
     * dentro de la librería Jackson que permite convertir objetos Java en su representación JSON y viceversa. En este caso,
     * se utiliza para escribir el cuerpo de la respuesta con los detalles del error en un formato JSON estructurado.</p>
     *
     * @param request La solicitud que resultó en un error de autenticación.
     * @param response La respuesta.
     * @param authException La excepción que causó la llamada a este método.
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws ServletException Si la solicitud no puede ser manejada.
     */

    @Override
    public void commence( HttpServletRequest request, HttpServletResponse response, AuthenticationException authException )
            throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());

        // Establece el tipo de contenido y el estado de la respuesta.

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Crea el cuerpo de la respuesta con detalles del error.

        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", authException.getMessage());
        body.put("path", request.getServletPath());

        // Escribe el cuerpo de la respuesta en formato JSON utilizando ObjectMapper.

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }

}