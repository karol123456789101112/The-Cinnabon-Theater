package org.example.repository;

import org.example.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findAllByOrderByNameAsc();
}
