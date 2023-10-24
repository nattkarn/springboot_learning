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
            "/user/activate",
            "/user/resend-avtivation-email"
    };

    private final String[] ALLOW_LIST = {
            "http://192.168.1.163:3000",
            "http://192.168.1.102:4200",
            "http://localhost:4200",
            "http://localhost:3000"
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
                .cors(cors -> cors.configurationSource(request -> corsConfiguration())) // Use the centralized CORS config
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(PUBLIC).permitAll()
                                .anyRequest().authenticated()
                )
                .apply(new TokenfilterConfigurer(tokenService));

        return http.build();
    }

    @Bean
    public CorsConfiguration corsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://192.168.1.163:3000");
//        config.addAllowedOrigin(Arrays.toString(ALLOW_LIST));
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        return config;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration()); // Use the centralized CORS config
        return new CorsFilter(source);
    }

}