package com.crudapp.users.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String message, String description, LocalDateTime timestamp)
{

}
