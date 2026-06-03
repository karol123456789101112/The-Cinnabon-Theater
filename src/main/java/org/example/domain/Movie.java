package org.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Duration;
import java.util.List;

@Entity
@Table(name = "movie")
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @Size(min = 1, max = 80)
    private String name;

    @NotNull
    @Size(min = 60, max = 360)
    private long duration;

    @OneToMany(mappedBy = "movie")
    private List<MovieScreening> movieScreenings;
}
