package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.Dto.MovieScreeningViewDto;
import org.example.Dto.ScreeningByDateDto;
import org.example.service.MovieScreeningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movieScreenings")
public class MovieScreeningController {

    public final MovieScreeningService movieScreeningService;

    @GetMapping
    public List<ScreeningByDateDto> getMovieScreenings(){
        return movieScreeningService.getMovieScreenings();
    }

    @GetMapping("/all")
    public List<MovieScreeningViewDto> getAllMovieScreenings(){return movieScreeningService.getAllMovieScreenings();}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovieScreening(@PathVariable("id") long id) {
        movieScreeningService.deleteMovieScreening(id);
        return ResponseEntity.noContent().build();
    }
}
