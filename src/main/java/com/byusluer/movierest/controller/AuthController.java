package com.byusluer.movierest.controller;

import com.byusluer.movierest.model.dto.request.LoginDto;
import com.byusluer.movierest.model.dto.request.RegisterDto;
import com.byusluer.movierest.model.dto.response.JwtAuthResponse;
import com.byusluer.movierest.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {

        String token = authService.login(loginDto);

        JwtAuthResponse response = new JwtAuthResponse(token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        String response = authService.register(registerDto);

        return ResponseEntity.ok(response);
    }

}
