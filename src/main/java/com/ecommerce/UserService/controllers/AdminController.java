package com.ecommerce.UserService.controllers;

import com.ecommerce.UserService.dto.response.UserResponseDTO;
import com.ecommerce.UserService.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PatchMapping("/activateuser/{id}")
    public ResponseEntity<UserResponseDTO> activateUser(@PathVariable Long id){
        return ResponseEntity.ok(adminService.activateUser(id));
    }

    @PatchMapping("/deactivateuser/{id}")
    public ResponseEntity<UserResponseDTO> deactivateUser(@PathVariable Long id){
        return ResponseEntity.ok(adminService.deactivateUser(id));
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getUserById(id));
    }


    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }



}
