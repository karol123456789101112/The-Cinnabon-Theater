package org.example.service;

import org.example.Dto.CreateMovieDto;
import org.example.Dto.MovieResponseDto;
import org.example.Dto.MovieViewDto;
import org.example.Dto.UpdateMovieDto;

import java.util.List;

public interface MovieService {
    List<MovieViewDto> getAllMovies();
    void deleteMovie(long id);
    MovieResponseDto addMovie(CreateMovieDto createMovieDto);
    MovieResponseDto editMovie(long id, UpdateMovieDto updateMovieDto);
}
