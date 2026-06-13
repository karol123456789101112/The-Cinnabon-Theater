package org.example.Dto;

import java.math.BigDecimal;

public record TicketResponseDto(
        long id,
        BigDecimal price,
        long seatId,
        long movieScreeningId,
        long userId
) {}
