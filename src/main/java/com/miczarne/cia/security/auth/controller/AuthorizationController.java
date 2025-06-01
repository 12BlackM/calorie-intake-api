package com.miczarne.cia.security.auth.controller;

import com.miczarne.cia.security.auth.model.AuthRequest;
import com.miczarne.cia.security.auth.model.AuthResponse;
import com.miczarne.cia.security.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthorizationController {

    private final AuthService userService;

    public AuthorizationController(AuthService userService) {
        this.userService = userService;
    }

    @PostMapping
    @RequestMapping(path = "/login")
    public AuthResponse authorizeUser(@RequestBody AuthRequest authRequest) {
        return userService.authenticate(authRequest);
    }

    @PostMapping
    @RequestMapping(path = "/register")
    public AuthResponse registerUser(@RequestBody AuthRequest authRequest) {
        return userService.register(authRequest);
    }
}
