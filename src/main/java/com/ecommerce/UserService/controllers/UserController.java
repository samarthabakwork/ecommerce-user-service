package com.ecommerce.UserService.controllers;

import com.ecommerce.UserService.dto.request.UpdateRequestDTO;
import com.ecommerce.UserService.dto.response.MessageResponseDTO;
import com.ecommerce.UserService.dto.response.UserResponseDTO;
import com.ecommerce.UserService.services.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/viewprofile")
    public UserResponseDTO viewProfile(){
        return userService.viewProfile();
    }

    @PatchMapping("/updateprofile")
    public UserResponseDTO updateProfile(@Valid @RequestBody UpdateRequestDTO dto){
        return userService.updateProfile(dto);
    }

    @DeleteMapping("/deleteaccount")
    public ResponseEntity<MessageResponseDTO> deleteAccount(){
        userService.deleteAccount();
        return ResponseEntity.ok(new MessageResponseDTO("Account deleted Successfully",true));
    }
}
