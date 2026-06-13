package org.example.Dto;

import org.example.domain.Role;

public record AppUserViewDto(
        long id,
        String firstName,
        String lastName,
        String email,
        Role role
) {}
