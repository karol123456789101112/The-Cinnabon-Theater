package org.example.repository;

import org.example.domain.MovieScreening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieScreeningRepository extends JpaRepository<MovieScreening, Long> {
    @Query("""
        SELECT DISTINCT ms FROM MovieScreening ms
        JOIN FETCH ms.movie m
        LEFT JOIN FETCH m.genres
        WHERE ms.active = true
    """)
    List<MovieScreening> findAllWithMoviesAndGenres();
    List<MovieScreening> findByActiveTrueOrderByStartTimeAsc();
}
