package com.example.pollapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*")
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // returns index.html
    }
    
    @GetMapping("/polls")
    public String polls() {
        return "polls"; // returns polls.html
    }

    @GetMapping("/about")
    public String about() {
        return "about"; // returns users.html
    }

    @GetMapping("/votes")
    public String votes() {
        return "votes"; // returns votes.html
    }
}
