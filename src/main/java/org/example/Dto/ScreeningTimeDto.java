package org.example.Dto;

import java.time.LocalTime;

public record ScreeningTimeDto(
        long id,
        LocalTime time
) {}
