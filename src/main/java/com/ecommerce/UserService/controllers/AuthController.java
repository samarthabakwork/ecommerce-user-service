package com.ecommerce.UserService.controllers;

import com.ecommerce.UserService.dto.request.LoginRequestDTO;
import com.ecommerce.UserService.dto.request.RegisterRequestDTO;
import com.ecommerce.UserService.dto.response.LoginResponseDTO;
import com.ecommerce.UserService.dto.response.RegisterResponseDTO;
import com.ecommerce.UserService.services.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //register
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO dto){
        RegisterResponseDTO response=authService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto){
        LoginResponseDTO response=authService.login(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
