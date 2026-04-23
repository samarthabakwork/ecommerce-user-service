package com.ecommerce.UserService.services.admin;

import com.ecommerce.UserService.dto.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {
    UserResponseDTO activateUser(Long id);
    UserResponseDTO deactivateUser(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    void deleteUser(Long id);
}
