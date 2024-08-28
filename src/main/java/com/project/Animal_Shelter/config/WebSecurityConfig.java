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
                .csrf(csrf ->
                        csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/test/all").permitAll()
                                .requestMatchers("/api/test/user").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/test/admin").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/newPost").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/post/delete/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/post/update/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/post/getAll").permitAll()
                                .requestMatchers("/api/v1/donations").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/v1/donations/delete/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/donations/update/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/donations/getAll").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
