package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Dto.MovieGroupDto;
import org.example.Dto.ScreeningByDateDto;
import org.example.Dto.ScreeningTimeDto;
import org.example.domain.Movie;
import org.example.domain.MovieScreening;
import org.example.repository.MovieScreeningRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MovieScreeningServiceImpl implements MovieScreeningService{

    private final MovieScreeningRepository movieScreeningRepository;

    @Override
    public List<ScreeningByDateDto> getMovieScreenings() {

        List<MovieScreening> screenings = movieScreeningRepository.findAll();

        Map<LocalDate, Map<Long, MovieGroupDto>> grouped = new HashMap<>();

        for (MovieScreening ms : screenings) {

            LocalDate date = ms.getStartTime().toLocalDate();
            LocalTime time = ms.getStartTime().toLocalTime();

            Movie movie = ms.getMovie();

            // sprawdź czy istnieje mapa dla daty
            if (!grouped.containsKey(date)) {
                grouped.put(date, new HashMap<>());
            }

            Map<Long, MovieGroupDto> moviesByDate = grouped.get(date);

            // sprawdź czy istnieje film
            if (!moviesByDate.containsKey(movie.getId())) {
                MovieGroupDto dto = new MovieGroupDto();
                dto.setMovieId(movie.getId());
                dto.setTitle(movie.getName());
                dto.setDuration(movie.getDuration());
                dto.setScreenings(new ArrayList<>());

                moviesByDate.put(movie.getId(), dto);
            }

            ScreeningTimeDto dto = new ScreeningTimeDto();

            dto.setId(ms.getId());
            dto.setTime(time);

            // dodaj godzinę
            moviesByDate
                    .get(movie.getId())
                    .getScreenings()
                    .add(dto);
        }

        // sortowanie godzin seansów
        for (Map<Long, MovieGroupDto> moviesByDate : grouped.values()) {
            for (MovieGroupDto movieDto : moviesByDate.values()) {
                movieDto.getScreenings().sort(Comparator.comparing(ScreeningTimeDto::getTime));
            }
        }

        return grouped.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(dateEntry -> {
                    ScreeningByDateDto dto = new ScreeningByDateDto();
                    dto.setDate(dateEntry.getKey());
                    dto.setMovies(new ArrayList<>(dateEntry.getValue().values()));
                    return dto;
                })
                .toList();
    }
}
