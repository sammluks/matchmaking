package com.matchmaking.matchmaking.dto;

import org.springframework.validation.FieldError;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidationErrorDto {
    private String field;
    private String message;

    public ValidationErrorDto(FieldError error) {
        this.field = error.getField();
        this.message = error.getDefaultMessage();
    }
}
