package TodoList.API.TodoListApi.Security;

import TodoList.API.TodoListApi.Security.exception.JwtAuthenticationEntryPoint;
import TodoList.API.TodoListApi.Security.filter.JwtFilter;
import TodoList.API.TodoListApi.Security.util.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtils jwtUtils;
    private final JwtFilter jwtFilter;
    private final JwtAuthenticationEntryPoint authEntryPoint;

    public SecurityConfig(JwtUtils jwtUtils, JwtFilter jwtFilter, JwtAuthenticationEntryPoint authEntryPoint) {
        this.jwtUtils = jwtUtils;
        this.jwtFilter = jwtFilter;
        this.authEntryPoint = authEntryPoint;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // Esto permite que los frames de H2 carguen correctamente
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}