package org.example.Dto;

public record UpdateAppUserDto(
        String email,
        String firstName,
        String lastName,
        String telephone,
        String password,
        String confirmPassword
) {}
