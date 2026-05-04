package com.ecommerce.UserService.services.user;

import com.ecommerce.UserService.dto.request.UpdateRequestDTO;
import com.ecommerce.UserService.dto.response.UserResponseDTO;
import com.ecommerce.UserService.security.UserPrincipal;

public interface UserService {
    UserResponseDTO viewProfile();
    UserResponseDTO updateProfile(UpdateRequestDTO dto);
    void deleteAccount();
}
