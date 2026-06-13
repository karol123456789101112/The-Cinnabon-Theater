package org.example.service;

import org.example.Dto.*;

import java.util.List;

public interface MovieScreeningService {
    List<ScreeningByDateDto> getMovieScreenings();
    List<MovieScreeningViewDto> getAllMovieScreenings();
    void deleteMovieScreening(long id);
    MovieScreeningResponseDto addMovieScreening(CreateMovieScreeningDto dto);
    MovieScreeningResponseDto editMovieScreening(long id, UpdateMovieScreeningDto dto);
}
