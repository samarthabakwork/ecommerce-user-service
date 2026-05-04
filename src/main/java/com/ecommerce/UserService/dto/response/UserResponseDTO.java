package com.ecommerce.UserService.dto.response;

import com.ecommerce.UserService.entities.Role;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private boolean active;
}
