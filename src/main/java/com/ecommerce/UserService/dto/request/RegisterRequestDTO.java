package com.ecommerce.UserService.dto.request;

import com.ecommerce.UserService.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDTO {
    @NotBlank(message = "name is required")
    @Size(max = 20,message = "name must not exceed 20 characters")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "email must be valid")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 4,max=10,message = "password must be atleast 4 characters")
    private String password;

    private Role role;

}

