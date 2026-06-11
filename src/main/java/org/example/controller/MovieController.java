package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.Dto.CreateMovieDto;
import org.example.Dto.MovieResponseDto;
import org.example.Dto.MovieViewDto;
import org.example.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public List<MovieViewDto> getAllMovies(){
        return movieService.getAllMovies();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addMovie")
    public ResponseEntity<MovieResponseDto> addMovie(@RequestBody CreateMovieDto createMovieDto) {
        return ResponseEntity.ok(movieService.addMovie(createMovieDto));
    }
}
