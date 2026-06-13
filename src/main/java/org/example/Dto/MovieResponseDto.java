package org.example.Dto;

import java.util.List;

public record MovieResponseDto(
        long id,
        String name,
        long duration,
        boolean active,
        List<GenreDto> genres
) {}
