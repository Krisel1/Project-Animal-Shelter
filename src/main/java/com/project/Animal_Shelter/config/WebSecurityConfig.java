package com.project.Animal_Shelter.config;

import com.project.Animal_Shelter.jwt.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final AuthTokenFilter authTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/v1/donations").hasRole("ADMIN")
                                .requestMatchers("/api/v1/donations/create").permitAll()
                                .requestMatchers("/api/v1/donations/update/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/donations/delete/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/donations/**").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/v1/pets/delete/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/pets/create").hasRole("ADMIN")
                                .requestMatchers("/api/v1/pets/update/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/pets").permitAll()
                                .requestMatchers("/api/v1/pets/**").permitAll()
                                .requestMatchers("/api/v1/pets/withoutAdopted").permitAll()
                                .requestMatchers("/api/v1/pets/adopted/**").permitAll()

//                                .requestMatchers("/profile/").authenticated()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
