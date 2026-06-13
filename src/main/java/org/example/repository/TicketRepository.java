package org.example.repository;

import org.example.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByReservedTrueOrderByIdAsc();
    List<Ticket> findByUserIdOrderByMovieScreeningStartTimeDesc(Long userId);
}
