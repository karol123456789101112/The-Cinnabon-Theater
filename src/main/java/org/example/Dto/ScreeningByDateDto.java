package org.example.Dto;

import java.time.LocalDate;
import java.util.List;

public record ScreeningByDateDto(
        LocalDate date,
        List<MovieGroupDto> movies
) {}
