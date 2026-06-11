package org.example.Dto;

import java.util.List;

public record UpdateMovieDto(
        String name,
        long duration,
        List<Long> genreIds
) {}
