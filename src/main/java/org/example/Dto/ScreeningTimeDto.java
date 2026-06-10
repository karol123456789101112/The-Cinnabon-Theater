package org.example.Dto;

import java.time.LocalTime;

public record ScreeningTimeDto(
        long screeningId,
        LocalTime time,
        long roomNumber
) {}