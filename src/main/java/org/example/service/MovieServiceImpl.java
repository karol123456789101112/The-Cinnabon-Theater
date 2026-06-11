package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Dto.CreateMovieDto;
import org.example.Dto.GenreDto;
import org.example.Dto.MovieResponseDto;
import org.example.Dto.MovieViewDto;
import org.example.domain.Genre;
import org.example.domain.Movie;
import org.example.repository.GenreRepository;
import org.example.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public List<MovieViewDto> getAllMovies() {
        List<Movie> allMovies = movieRepository.findAll();

        List<MovieViewDto> dto = new ArrayList<>();

        for(Movie movie : allMovies){
            if (movie.isActive()) {
                dto.add(new MovieViewDto(
                        movie.getId(),
                        movie.getName(),
                        movie.getDuration()
                ));
            }
        }

        return dto;
    }

    @Transactional
    public void deleteMovie(long id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        movie.setActive(false);
    }

    public MovieResponseDto addMovie(CreateMovieDto createMovieDto) {
        Movie movie = new Movie();

        List<Genre> genres = genreRepository.findAllById(createMovieDto.genreIds());

        movie.setActive(true);
        movie.setName(createMovieDto.name());
        movie.setDuration(createMovieDto.duration());
        movie.setGenres(genres);

        Movie saved = movieRepository.save(movie);

        List<GenreDto> genreDtos = saved.getGenres()
                .stream()
                .map(g -> new GenreDto(g.getId(), g.getName()))
                .toList();

        return new MovieResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getDuration(),
                saved.isActive(),
                genreDtos
        );
    }
}
