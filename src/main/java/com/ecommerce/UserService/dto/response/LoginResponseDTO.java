package com.ecommerce.UserService.dto.response;

import com.ecommerce.UserService.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private String name;
    private Role role;
    private String token;
}
