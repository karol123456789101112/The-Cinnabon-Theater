package org.example.service;

import org.example.Dto.MovieScreeningViewDto;
import org.example.Dto.ScreeningByDateDto;

import java.util.List;

public interface MovieScreeningService {
    List<ScreeningByDateDto> getMovieScreenings();
    List<MovieScreeningViewDto> getAllMovieScreenings();
}
