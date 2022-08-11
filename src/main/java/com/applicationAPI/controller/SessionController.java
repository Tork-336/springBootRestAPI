package com.applicationAPI.controller;

import com.applicationAPI.configuration.JwtUtil;
import com.applicationAPI.controller.Form.LoginForm;
import com.applicationAPI.services.interfaces.SessionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class SessionController {

    private final SessionService service;

    public SessionController(SessionService service) {
        this.service = service;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginForm user) throws JsonProcessingException {
        LoginForm userSession = service.login(user);
        UsernamePasswordAuthenticationToken addSession = new UsernamePasswordAuthenticationToken(userSession, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(addSession);
        return new ResponseEntity<>(JwtUtil.generateToken(userSession), HttpStatus.OK);
    }
}
