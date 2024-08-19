package com.crudapp.users.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO (Long id,
                       @NotBlank(message = "Username must not be null")
                       String username,
                       @NotBlank(message = "Password is mandatory")
                       String password,
                       @NotNull(message = "Specify the active Status")
                       Boolean active){

}


