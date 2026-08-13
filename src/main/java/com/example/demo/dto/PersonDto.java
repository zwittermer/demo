package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Long id;

    @NotBlank(message = "name must not be blank")
    @Pattern(
        regexp = "^[A-Za-z][A-Za-z0-9+\\-_/$%&]*$",
        message = "name must start with a letter and contain only letters, digits, and the symbols +-_/$%&"
    )
    private String name;

    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be a valid email address")
    private String email;
}
