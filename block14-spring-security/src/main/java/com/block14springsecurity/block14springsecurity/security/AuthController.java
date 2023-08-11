package com.block14springsecurity.block14springsecurity.security;

import com.block14springsecurity.block14springsecurity.mapper.PersonaMapper;
import com.block14springsecurity.block14springsecurity.security.jwt.JwtUtils;
import com.block14springsecurity.block14springsecurity.security.response.JwtResponseDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Controlador que gestiona las operaciones relacionadas con la autenticación de usuarios.
 * Proporciona endpoints para autenticar usuarios y generar tokens JWT para sesiones autenticadas.
 *
 * <p>El controlador utiliza varios componentes, como {@link AuthenticationManager} para autenticar usuarios,
 * {@link PersonaAuthService} para realizar operaciones relacionadas con el servicio de autenticación de personas,
 * {@link JwtUtils} para operaciones de token JWT y {@link PersonaMapper} para convertir entre entidades y DTOs.</p>
 *
 * @author Tu nombre aquí
 */

@RestController

@RequestMapping("/api/auth")
public class AuthController {

    /**
     * Logger para registrar eventos relacionados con las operaciones de autenticación.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    /**
     * Gestor utilizado para autenticar usuarios.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Servicio que gestiona operaciones específicas de autenticación de personas.
     */
    private final PersonaAuthService personaAuthService;

    /**
     * Utilidad para gestionar operaciones relacionadas con tokens JWT.
     */
    private final JwtUtils jwtUtils;

    /**
     * Mapper utilizado para convertir entre entidades y DTOs relacionados con personas.
     */
    private final PersonaMapper personaMapper;

    /**
     * Constructor para inicializar {@link AuthController} con dependencias requeridas.
     *
     * @param authenticationManager Gestor de autenticación.
     * @param personaAuthService    Servicio de autenticación de personas.
     * @param jwtUtils              Utilidad de JWT.
     * @param personaMapper         Mapper de personas.
     */

    public AuthController( AuthenticationManager authenticationManager, PersonaAuthService personaAuthService, JwtUtils jwtUtils, PersonaMapper personaMapper ) {
        this.authenticationManager = authenticationManager;
        this.personaAuthService = personaAuthService;
        this.jwtUtils = jwtUtils;
        this.personaMapper = personaMapper;
    }

    /**
     * Endpoint para autenticar usuarios. Una vez autenticados, genera y retorna un token JWT.
     *
     * <p>El token JWT es utilizado para futuras solicitudes autenticadas en la aplicación.
     * Además de generar el token, este método también recopila detalles del usuario autenticado,
     * como su ID, nombre de usuario, rol principal y otros roles asociados.</p>
     *
     * @param user Detalles del usuario para autenticar.
     *             Estos detalles incluyen el nombre de usuario y la contraseña.
     * @return Una respuesta que contiene el token JWT y detalles relacionados del usuario autenticado.
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser( @Valid @RequestBody UserDetailsImpl user ) {
        LOGGER.info("Attempting authentication for user: {}", user.getUsername());


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        LOGGER.info("Authentication successful for user: {}", userDetails.getUsername());

        return ResponseEntity.ok(new JwtResponseDto(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getRole(),
                roles));
    }

  /**  @PostMapping("/signup")
    public ResponseEntity<?> registerPassenger( @Valid @RequestBody SignupPassengerDto signUpPassengerDto ) {
        try {
            PassengerDTO passengerDTO = passengerAuthService.registerPassenger(signUpPassengerDto);
            return ResponseEntity.ok(new MessageResponseDto("User registered successfully!"));

        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponseDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  **/
}