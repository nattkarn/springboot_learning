package com.testing.api.backend.config;

import com.testing.api.backend.config.token.TokenfilterConfigurer;
import com.testing.api.backend.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final TokenService tokenService;

    private final String[] PUBLIC = {
            "/user/login",
            "/user/register",
            "/actuator/**",
            "/socket/**",
            "/chat/**" //TODO: remove from config
    };

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
//                .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
//                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorizeRequests ->
                                authorizeRequests
//                                .requestMatchers(HttpMethod.POST, "/user/login", "/user/register", "/actuator/**").permitAll() //for best security use HttpMethod
                                        .requestMatchers(PUBLIC).permitAll()
                                        .anyRequest().authenticated()

                )
                .apply(new TokenfilterConfigurer(tokenService));

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
//        config.addAllowedOriginPattern(String.valueOf(Arrays.asList("http://localhost*")));
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}