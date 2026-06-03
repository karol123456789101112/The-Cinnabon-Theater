package org.example.Dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ScreeningTimeDto {
    private long id;
    private LocalTime time;
}
