package com.testing.api.backend.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class HomeApi {

    @GetMapping("/")
    public String root() {
        return "redirect:/swagger-ui/index.html#/";
    }


}
