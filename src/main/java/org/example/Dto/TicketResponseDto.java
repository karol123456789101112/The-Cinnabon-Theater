package org.example.Dto;

public record TicketResponseDto(
        long id,
        long seatId,
        long movieScreeningId
) {}
