package org.example.Dto;

public record AppUserResponseDto(
        long id,
        String email,
        String firstName,
        String lastName,
        String telephone
) {}
