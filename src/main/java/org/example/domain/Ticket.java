package org.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_ticket_seat_screening",
                        columnNames = {"seat_id", "movie_screening_id"}
                )
        }
)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    private boolean reserved;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "movie_screening_id")
    private MovieScreening movieScreening;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
}
