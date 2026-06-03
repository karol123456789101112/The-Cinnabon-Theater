package org.example.Dto;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class MovieScreeningDto {
    private long id;
    private String movieTitle;
    private Duration duration;
    private LocalDate date;
    private LocalTime startTime;
    private long screeningRoomNumber;
}
