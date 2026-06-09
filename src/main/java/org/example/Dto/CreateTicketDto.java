package org.example.Dto;

import java.math.BigDecimal;

public record CreateTicketDto(
   long seatId,
   long movieScreeningId
) {}
