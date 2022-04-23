package com.prograCol.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/status")
    public String getStatus() {
        return " This is server, status run..!! ";
    }
}
