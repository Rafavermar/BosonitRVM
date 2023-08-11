package com.block14springsecurity.block14springsecurity.security.jwt;

import com.block14springsecurity.block14springsecurity.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Utilidad para gestionar operaciones relacionadas con tokens JWT (JSON Web Tokens).
 * Proporciona funcionalidad para generar, validar, y extraer detalles de los tokens JWT.
 *
 * <p>Los tokens JWT son utilizados para autenticar y autorizar solicitudes en aplicaciones web.
 * Esta clase se utiliza para abstraer las operaciones comunes relacionadas con JWT, como la generación
 * de tokens para usuarios autenticados y la validación de tokens entrantes.</p>
 *
 *
 * @see <a href="https://jwt.io/">JWT.io</a>
 */

@Component
public class JwtUtils {

    /**
     * Logger para registrar eventos relacionados con operaciones JWT.
     */
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * Secreto utilizado para firmar y validar tokens JWT.
     */

    @Value("${block14.security.jwtSecret}")
    private String jwtSecret;

    /**
     * Duración en milisegundos para la expiración del token JWT.
     */

    @Value("${block14.security.jwtExpirationMs}")
    private int jwtExpirationMs;

    /**
     * Genera un token JWT para un usuario autenticado.
     *
     * @param authentication Información de autenticación del usuario.
     * @return Un token JWT firmado.
     */

    public String generateJwtToken( Authentication authentication ) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .claim("role", userPrincipal.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    /**
     * Genera una clave para firmar y validar el token JWT basado en el secreto configurado.
     *
     * @return Una clave basada en el secreto JWT.
     */
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    /**
     * Extrae el nombre de usuario desde el token JWT proporcionado.
     *
     * @param token El token JWT.
     * @return El nombre de usuario contenido en el token JWT.
     */
    public String getUserNameFromJwtToken( String token ) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }


    /**
     * Valida el token JWT proporcionado.
     *
     * <p>La validación incluye la verificación de la firma, la expiración, y la estructura general del token.</p>
     *
     * @param authToken El token JWT a validar.
     * @return True si el token es válido; de lo contrario, false.
     */
    public boolean validateJwtToken( String authToken ) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    /**
     * Extrae el rol del usuario desde el token JWT proporcionado.
     *
     * @param token El token JWT.
     * @return El rol del usuario contenido en el token JWT.
     */
    public String getRoleFromJwtToken( String token ) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.get("role", String.class);
    }


}