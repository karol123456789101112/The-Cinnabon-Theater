package org.example.repository;

import org.example.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m JOIN FETCH m.genres WHERE m.active = true ORDER BY m.name ASC")
    List<Movie> findAllActiveWithGenres();
}
