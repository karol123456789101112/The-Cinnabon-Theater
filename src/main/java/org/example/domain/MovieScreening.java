package org.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import org.example.domain.ScreeningRoom;

@Entity
@Table(name = "movie_screening")
@Data
public class MovieScreening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private BigDecimal price;

    @NotNull
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "screening_room_id")
    private ScreeningRoom screeningRoom;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "movieScreening")
    private List<Ticket> tickets;

}
