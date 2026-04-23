package com.ecommerce.UserService.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateRequestDTO {
    @NotBlank(message = "Name should not be empty")
    private String name;

    @Email
    private String email;
}
