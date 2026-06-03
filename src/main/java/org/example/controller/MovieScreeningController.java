package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.Dto.MovieScreeningDto;
import org.example.Dto.ScreeningByDateDto;
import org.example.service.MovieScreeningService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
