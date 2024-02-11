package org.itstep.welcome_spring.app.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("")
    public String main() {
        return "Hello World";
    }
}
