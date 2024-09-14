package com.example.pollapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index"; // Returns the name of the view (index.html or home.jsp)
    }
}
