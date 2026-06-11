package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Dto.MovieGroupDto;
import org.example.Dto.MovieScreeningViewDto;
import org.example.Dto.ScreeningByDateDto;
import org.example.Dto.ScreeningTimeDto;
import org.example.domain.Movie;
import org.example.domain.MovieScreening;
import org.example.repository.MovieScreeningRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MovieScreeningServiceImpl implements MovieScreeningService {

    private final MovieScreeningRepository movieScreeningRepository;

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
                dto.add(new MovieScreeningViewDto(movieScreening.getId(), movieScreening.getStartTime()));
            }
        }

        return dto;
    }

    @Transactional
    public void deleteMovieScreening(long id) {
        MovieScreening movieScreening = movieScreeningRepository.findById(id).orElseThrow();
        movieScreening.setActive(false);
    }
}