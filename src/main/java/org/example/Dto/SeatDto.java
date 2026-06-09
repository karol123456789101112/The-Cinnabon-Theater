package org.example.Dto;

public record SeatDto(
        long id,
        String row,
        int number,
        boolean reserved
) {}
