package org.example.Dto;

import java.util.List;

public record CreateMovieDto(
    String name,
    long duration,
    List<Long> genreIds
) {}
