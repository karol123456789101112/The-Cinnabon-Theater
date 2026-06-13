package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.Dto.GenreDto;
import org.example.service.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<GenreDto> getAllGenres() {
        return genreService.getAllGenres();
    }
}
