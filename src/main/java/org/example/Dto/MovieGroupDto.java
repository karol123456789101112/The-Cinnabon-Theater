package org.example.Dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MovieGroupDto {
    private long movieId;
    private String title;
    private long duration;
    private List<ScreeningTimeDto> screenings = new ArrayList<>();
}