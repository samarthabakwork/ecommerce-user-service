package com.ecommerce.UserService.dto.response;

import com.ecommerce.UserService.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private Role role;

}
