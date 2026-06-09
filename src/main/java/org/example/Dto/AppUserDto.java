package org.example.Dto;

public record AppUserDto(
        String firstName,
        String lastName,
        String email,
        String telephone,
        String password,
        String confirmPassword,
        String recaptchaToken
) {}
