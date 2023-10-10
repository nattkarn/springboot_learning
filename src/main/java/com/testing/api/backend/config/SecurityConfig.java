package com.testing.api.backend.config;

import com.testing.api.backend.config.token.TokenfilterConfigurer;
import com.testing.api.backend.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final TokenService tokenService;

    public SecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
//                                .requestMatchers(HttpMethod.POST, "/user/login", "/user/register", "/actuator/**").permitAll() //for best security use HttpMethod
                                .requestMatchers("/user/login", "/user/register", "/actuator/**").permitAll()
                                .anyRequest().authenticated()

                )
                .apply(new TokenfilterConfigurer(tokenService));

        return http.build();
    }



//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.disable())
//                .csrf(csrf -> csrf.disable())
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers(HttpMethod.POST, "/user/login", "/user/register").permitAll()
//                                .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }
}