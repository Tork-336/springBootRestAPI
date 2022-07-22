package com.applicationAPI.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/status", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getStatus() {
        return " This is server, status run..!! ";
    }
}
