package com.block14springsecurity.block14springsecurity.security.config;

import com.block14springsecurity.block14springsecurity.security.UserDetailsServiceImpl;
import com.block14springsecurity.block14springsecurity.security.jwt.AuthEntryPointJwt;
import com.block14springsecurity.block14springsecurity.security.jwt.AuthTokenFilter;
import com.block14springsecurity.block14springsecurity.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


/**
 * Configuración principal de seguridad para la aplicación web.
 * Esta clase define la configuración necesaria para la autenticación y autorización utilizando JWT (JSON Web Tokens).
 *
 *
 */

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    // Dependencias autowired gracias a @RequiredArgsConstructor de Lombok.

    /**
     * Implementación de servicio de detalles de usuario.
     * Utilizado para recuperar detalles de un usuario a partir de su username.
     */

    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Punto de entrada para manejar excepciones relacionadas con autenticación no autorizada.
     */
    private final AuthEntryPointJwt unauthorizedHandler;

    /**
     * Utilidad para trabajar con JWT, como generación y validación de tokens.
     */
    private final JwtUtils jwtUtils;

    /**
     * Configura el filtro de token de autenticación.
     * Este filtro se encarga de extraer y validar el JWT del encabezado de las solicitudes.
     *
     * @return El filtro de token de autenticación.
     */

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter(jwtUtils, userDetailsService);
    }

    /**
     * Proveedor de autenticación basado en DAO (Data Access Object).
     * Se encarga de recuperar detalles del usuario y comparar las contraseñas para la autenticación.
     *
     * @return El proveedor de autenticación configurado.
     */

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configura el gestor de autenticación.
     * Este gestor se encarga de autenticar a los usuarios en el sistema.
     *
     * @param authConfig Configuración de autenticación.
     * @return El gestor de autenticación.
     * @throws Exception Si hay algún error en la configuración.
     */

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration authConfig ) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Proveedor del codificador de contraseñas.
     * Se utiliza para codificar y comparar contraseñas en el sistema.
     *
     * @return El codificador de contraseñas.
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Configura la cadena de filtros de seguridad.
     * Define cómo se deben tratar las solicitudes en términos de autenticación y autorización.
     *
     * <p>Desglose de configuraciones:</p>
     * <ul>
     *   <li><b>cors(withDefaults())</b>: Habilita la configuración predeterminada de CORS (Cross-Origin Resource Sharing).</li>
     *   <li><b>csrf(AbstractHttpConfigurer::disable)</b>: Desactiva la protección CSRF (Cross-Site Request Forgery). Esto es común al usar tokens como JWT ya que no mantienen estado.</li>
     *   <li><b>exceptionHandling</b>: Define el manejo de excepciones relacionadas con la autenticación y autorización.</li>
     *   <li><b>sessionManagement</b>: Configura cómo se manejan las sesiones. En este caso, se define que no se creen sesiones, lo cual es típico en aplicaciones sin estado que usan JWT.</li>
     *   <li><b>authorizeHttpRequests</b>: Configura las reglas de autorización para las peticiones HTTP. Aquí se define que el endpoint `/api/auth/login` es de acceso libre y cualquier otra solicitud debe estar autenticada.</li>
     * </ul>
     *
     * Además, se añade el `authenticationProvider` y se configura el `authTokenFilter` para que se aplique antes del filtro `UsernamePasswordAuthenticationFilter`, asegurando que primero se valide el JWT antes de procesar las credenciales de usuario y contraseña.
     *
     * @param http Configuración de seguridad HTTP.
     * @return La cadena de filtros de seguridad configurada.
     * @throws Exception Si hay algún error en la configuración.
     */
    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        http.cors(withDefaults()).csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/login").permitAll()
                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

//.requestMatchers("/h2-console/**").permitAll()