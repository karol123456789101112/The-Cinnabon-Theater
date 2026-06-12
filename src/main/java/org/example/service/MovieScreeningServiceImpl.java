package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Dto.*;
import org.example.domain.Movie;
import org.example.domain.MovieScreening;
import org.example.domain.ScreeningRoom;
import org.example.repository.MovieRepository;
import org.example.repository.MovieScreeningRepository;
import org.example.repository.ScreeningRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MovieScreeningServiceImpl implements MovieScreeningService {

    private final MovieScreeningRepository movieScreeningRepository;
    private final MovieRepository movieRepository;
    private final ScreeningRoomRepository screeningRoomRepository;

    @Override
    public List<ScreeningByDateDto> getMovieScreenings() {

        List<MovieScreening> screenings =
                movieScreeningRepository.findAllWithMoviesAndGenres();

        Map<LocalDate, Map<Long, MovieGroupDto>> grouped = new HashMap<>();

        for (MovieScreening ms : screenings) {

            LocalDate date = ms.getStartTime().toLocalDate();
            LocalTime time = ms.getStartTime().toLocalTime();

            Movie movie = ms.getMovie();

            // data
            grouped.putIfAbsent(date, new HashMap<>());
            Map<Long, MovieGroupDto> moviesByDate = grouped.get(date);

            // film
            moviesByDate.putIfAbsent(
                    movie.getId(),
                    new MovieGroupDto(
                            movie.getId(),
                            movie.getName(),
                            movie.getDuration(),
                            movie.getGenres()
                                    .stream()
                                    .map(g -> g.getName())
                                    .distinct()
                                    .toList(),
                            new ArrayList<>()
                    )
            );

            MovieGroupDto movieDto = moviesByDate.get(movie.getId());
            movieDto.screenings().add(
                    new ScreeningTimeDto(
                            ms.getId(),
                            time,
                            ms.getScreeningRoom().getRoomNumber()
                    )
            );
        }

        // sortowanie
        for (Map<Long, MovieGroupDto> moviesByDate : grouped.values()) {
            for (MovieGroupDto movieDto : moviesByDate.values()) {

                movieDto.screenings().sort(
                        Comparator.comparing(ScreeningTimeDto::time)
                );
            }
        }

        return grouped.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(dateEntry ->
                        new ScreeningByDateDto(
                                dateEntry.getKey(),
                                new ArrayList<>(dateEntry.getValue().values())
                        )
                )
                .toList();
    }

    public List<MovieScreeningViewDto> getAllMovieScreenings(){
        List<MovieScreening> allMovieScreenings = movieScreeningRepository.findAll();

        List<MovieScreeningViewDto> dto = new ArrayList<>();

        for(MovieScreening movieScreening : allMovieScreenings){
            if (movieScreening.isActive()) {
                dto.add(new MovieScreeningViewDto(
                            movieScreening.getId(),
                            movieScreening.getPrice(),
                            movieScreening.getStartTime(),
                            movieScreening.getMovie().getId(),
                            movieScreening.getScreeningRoom().getId()
                        ));
            }
        }

        return dto;
    }

    @Transactional
    public void deleteMovieScreening(long id) {
        MovieScreening movieScreening = movieScreeningRepository.findById(id).orElseThrow();
        movieScreening.setActive(false);
    }

    public MovieScreeningResponseDto addMovieScreening(CreateMovieScreeningDto dto) {

        Movie movie = movieRepository.findById(dto.movieId()).orElseThrow();
        ScreeningRoom screeningRoom = screeningRoomRepository.findById(dto.screeningRoomId()).orElseThrow();

        MovieScreening movieScreening = new MovieScreening();

        movieScreening.setActive(true);
        movieScreening.setPrice(dto.price());
        movieScreening.setStartTime(dto.startTime());
        movieScreening.setMovie(movie);
        movieScreening.setScreeningRoom(screeningRoom);

        MovieScreening saved = movieScreeningRepository.save(movieScreening);

        MovieSummaryDto mdto = new MovieSummaryDto(saved.getMovie().getId(), saved.getMovie().getName());
        ScreeningRoomDto sdto = new ScreeningRoomDto(saved.getScreeningRoom().getId(),
                saved.getScreeningRoom().getRoomNumber());

        return new MovieScreeningResponseDto(
                saved.getId(),
                saved.getPrice(),
                saved.getStartTime(),
                mdto,
                sdto
        );
    }

    public MovieScreeningResponseDto editMovieScreening(long id, UpdateMovieScreeningDto dto) {
        Movie movie = movieRepository.findById(dto.movieId()).orElseThrow();
        ScreeningRoom screeningRoom = screeningRoomRepository.findById(dto.screeningRoomId()).orElseThrow();

        MovieScreening movieScreening = movieScreeningRepository.findById(id).orElseThrow();

        movieScreening.setActive(true);
        movieScreening.setPrice(dto.price());
        movieScreening.setStartTime(dto.startTime());
        movieScreening.setMovie(movie);
        movieScreening.setScreeningRoom(screeningRoom);

        MovieScreening saved = movieScreeningRepository.save(movieScreening);

        MovieSummaryDto mdto = new MovieSummaryDto(saved.getMovie().getId(), saved.getMovie().getName());
        ScreeningRoomDto sdto = new ScreeningRoomDto(saved.getScreeningRoom().getId(),
                saved.getScreeningRoom().getRoomNumber());

        return new MovieScreeningResponseDto(
                saved.getId(),
                saved.getPrice(),
                saved.getStartTime(),
                mdto,
                sdto
        );
    }
}