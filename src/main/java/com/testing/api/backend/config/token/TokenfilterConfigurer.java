package com.testing.api.backend.config.token;

import com.testing.api.backend.service.TokenService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TokenfilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenService service;

    public TokenfilterConfigurer(TokenService service) {
        this.service = service;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        TokenFilter filter = new TokenFilter(service);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
