package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.Dto.MovieViewDto;
import org.example.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
