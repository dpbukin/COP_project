package com.example.cop_rut.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPageController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}