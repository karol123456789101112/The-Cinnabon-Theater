package org.example.service;

import org.example.Dto.MovieViewDto;

import java.util.List;

public interface MovieService {
    List<MovieViewDto> getAllMovies();
}
