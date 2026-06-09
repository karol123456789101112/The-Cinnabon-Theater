package org.example.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ReservationScreeningDto(
        LocalDate date,
        String movieTitle,
        long duration,
        LocalTime startTime,
        long roomNumber,
        BigDecimal price,
        List<SeatDto> seats
){}