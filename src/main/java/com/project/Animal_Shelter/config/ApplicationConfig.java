package com.project.Animal_Shelter.config;

import com.project.Animal_Shelter.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final IUserRepository iUserRepository;

    @Bean
    public AuthenticationManager authenticationManager(AutheticationConfiguration Configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(UserDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public PasswordEnconder passwordEnconder() {
        return new BCryptPasswordEncoder();
    }

        @Bean
        public UserDetailsService userDetailsService() {
            return username -> iUserRepository.findByUsername(username)
                    .orElseThrow()) -> new UsernameNotFoundException("No existe el usuario"));

        }
}
