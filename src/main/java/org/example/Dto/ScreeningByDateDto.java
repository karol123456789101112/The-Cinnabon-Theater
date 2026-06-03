package org.example.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ScreeningByDateDto {
    private LocalDate date;
    private List<MovieGroupDto> movies = new ArrayList<>();
}
