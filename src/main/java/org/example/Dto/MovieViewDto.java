package org.example.Dto;

import java.util.List;

public record MovieViewDto(
        long id,
        String name,
        long duration,
        List<GenreDto> genres
) {}
