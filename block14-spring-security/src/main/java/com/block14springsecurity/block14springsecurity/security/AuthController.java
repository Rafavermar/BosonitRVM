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


@RestController

@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final PersonaAuthService personaAuthService;
    private final JwtUtils jwtUtils;
    private final PersonaMapper personaMapper;

    public AuthController( AuthenticationManager authenticationManager, PersonaAuthService personaAuthService, JwtUtils jwtUtils, PersonaMapper personaMapper ) {
        this.authenticationManager = authenticationManager;
        this.personaAuthService = personaAuthService;
        this.jwtUtils = jwtUtils;
        this.personaMapper = personaMapper;
    }


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