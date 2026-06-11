package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Dto.*;
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
        return movieRepository.findAllActiveWithGenres()
                .stream()
                .map(movie -> new MovieViewDto(
                        movie.getId(),
                        movie.getName(),
                        movie.getDuration(),
                        movie.getGenres()
                                .stream()
                                .map(g -> new GenreDto(g.getId(), g.getName()))
                                .toList()
                ))
                .toList();
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

    public MovieResponseDto editMovie(long id, UpdateMovieDto updateMovieDto) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        List<Genre> allGenres = genreRepository.findAllById(updateMovieDto.genreIds());

        movie.setName(updateMovieDto.name());
        movie.setDuration(updateMovieDto.duration());
        movie.setGenres(allGenres);

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
