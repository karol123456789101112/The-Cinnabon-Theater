package org.example.Dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketUserResponseDto(
        long id,
        LocalDateTime createdAt,
        BigDecimal price,
        LocalDateTime startTime,
        String movieName,
        int seatNumber,
        String row,
        long roomNumber,
        long duration,
        boolean reserved,
        String userEmail
) {}
