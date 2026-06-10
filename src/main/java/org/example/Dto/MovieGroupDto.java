package org.example.Dto;

import java.util.List;

public record MovieGroupDto(
   long movieId,
   String title,
   long duration,
   List<String> genres,
   List<ScreeningTimeDto> screenings
) {}
