package org.example.Dto;

import java.time.LocalDateTime;

public record MovieScreeningViewDto(
        long id,
        LocalDateTime startTime
) {}
