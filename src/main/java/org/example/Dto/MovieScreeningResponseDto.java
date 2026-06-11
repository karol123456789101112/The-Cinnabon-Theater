package org.example.Dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovieScreeningResponseDto(
   long id,
   BigDecimal price,
   LocalDateTime startTime,
   MovieSummaryDto movie,
   ScreeningRoomDto screeningRoom
) {}
