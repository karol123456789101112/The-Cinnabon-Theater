package org.example.Dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateMovieScreeningDto(
        BigDecimal price,
        LocalDateTime startTime,
        long movieId,
        long screeningRoomId
) {}
