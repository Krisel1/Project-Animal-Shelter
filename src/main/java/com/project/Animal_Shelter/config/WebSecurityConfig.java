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
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/pets/withoutAdopted").permitAll()
                                .requestMatchers("/pets").hasRole("ADMIN")
                                .requestMatchers("/pets/delete/**").hasRole("ADMIN")
                                .requestMatchers("/pets/create").hasRole("ADMIN")
                                .requestMatchers("/pets/update/**").hasRole("ADMIN")
                                .requestMatchers("/donations").hasRole("ADMIN")
                                .requestMatchers("/donations/create").authenticated()
                                .requestMatchers("/donations/delete/**").hasRole("ADMIN")
                                .requestMatchers("/donations/update/**").hasRole("ADMIN")
                                .requestMatchers("/donations/getAllByUser/**").authenticated()
                                .requestMatchers("/pets/adopt/**").authenticated()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
