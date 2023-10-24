package com.testing.api.backend.api;

import org.springframework.web.bind.annotation.GetMapping;


public class HomeApi {

    @GetMapping("/")
    public String root() {
        return "index";
    }


}
