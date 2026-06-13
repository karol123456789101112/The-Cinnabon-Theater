package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Dto.GenreDto;
import org.example.domain.Genre;
import org.example.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public List<GenreDto> getAllGenres() {
        List<GenreDto> allGenres =  genreRepository.findAllByOrderByNameAsc()
                .stream()
                .map(g -> new GenreDto(g.getId(), g.getName()))
                .toList();
        
        return allGenres;
    }
}
