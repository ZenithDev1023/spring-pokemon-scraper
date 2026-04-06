package com.pokemon.analysis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        .csrf(csrf -> csrf.disable())

        .authorizeHttpRequests(registry -> registry
            .requestMatchers("/login", "/signup", "/index", "/**", "/api/pokemon/**", "/type/**", "/pokemondb/**", "/scrapeme/**", "/type/**", "/css/**", "/images/**", "/static/**")
            .permitAll()
            .anyRequest().authenticated()
         )

        .formLogin(httpForm -> httpForm
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/", true)
            .permitAll()
        )
        
        .build();
    }

}
