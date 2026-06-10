package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Dto.MovieViewDto;
import org.example.domain.Movie;
import org.example.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public List<MovieViewDto> getAllMovies(){
        List<Movie> allMovies = movieRepository.findAll();

        List<MovieViewDto> dto = new ArrayList<>();

        for(Movie movie : allMovies){
            dto.add(new MovieViewDto(
                    movie.getId(),
                    movie.getName(),
                    movie.getDuration()
            ));
        }

        return dto;
    }
}
