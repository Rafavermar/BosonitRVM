package com.block14springsecurity.block14springsecurity.security.jwt;

import com.block14springsecurity.block14springsecurity.security.UserDetailsImpl;
import com.block14springsecurity.block14springsecurity.security.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro personalizado que se encarga de extraer y validar el token JWT de las solicitudes entrantes.
 * Si el token es válido, configura la autenticación en el contexto de seguridad para la solicitud actual.
 *
 * <p>Este filtro hereda de {@link OncePerRequestFilter}, asegurando que se procesa una vez por solicitud.
 * La clase base `OncePerRequestFilter` es parte del Spring Framework y está diseñada para garantizar que
 * un filtro específico se aplique exactamente una vez en una única solicitud.</p>
 *
 * <p>El proceso de filtrado consiste en:</p>
 * <ul>
 *   <li>Extraer el token de la cabecera de la solicitud.</li>
 *   <li>Validar el token.</li>
 *   <li>Extraer el nombre de usuario y el rol del token.</li>
 *   <li>Validar que el rol en el token coincida con el rol en los detalles del usuario cargados.</li>
 *   <li>Establecer la autenticación en el contexto de seguridad si todo es válido.</li>
 * </ul>
 *
 *
 * @see OncePerRequestFilter
 */

@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    /**
     * Utilidad para las operaciones relacionadas con JWT, como la validación y la extracción de detalles.
     */

    private final JwtUtils jwtUtils;

    /**
     * Servicio para cargar detalles de un usuario, basado en su nombre de usuario.
     */

    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Logger para registrar eventos relacionados con la autenticación basada en token.
     */

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    /**
     * Realiza el filtrado interno para una solicitud y respuesta. Extrae y valida el token JWT.
     * Si el token es válido, establece la autenticación en el contexto de seguridad.
     *
     * <p>El proceso consiste en:</p>
     * <ul>
     *   <li>Extraer el token de la cabecera de la solicitud.</li>
     *   <li>Validar el token.</li>
     *   <li>Extraer el nombre de usuario y el rol del token.</li>
     *   <li>Validar que el rol en el token coincida con el rol en los detalles del usuario cargados.</li>
     *   <li>Establecer la autenticación en el contexto de seguridad si todo es válido.</li>
     * </ul>
     *
     * @param request La solicitud HTTP entrante.
     * @param response La respuesta HTTP.
     * @param filterChain Cadena de filtros.
     * @throws ServletException Si ocurre un error en el filtrado.
     * @throws IOException Si ocurre un error de entrada/salida.
     */

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain )
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            logger.info("JWT Token: {}", jwt);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                logger.info("Username: {}", username);
                String role = jwtUtils.getRoleFromJwtToken(jwt);
                logger.info("Role: {}", role);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (role.equals(((UserDetailsImpl) userDetails).getRole())) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }


    private String parseJwt( HttpServletRequest request ) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
