package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Dto.CreateTicketDto;
import org.example.Dto.TicketResponseDto;
import org.example.domain.AppUser;
import org.example.domain.MovieScreening;
import org.example.domain.Seat;
import org.example.domain.Ticket;
import org.example.repository.AppUserRepository;
import org.example.repository.MovieScreeningRepository;
import org.example.repository.SeatRepository;
import org.example.repository.TicketRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final MovieScreeningRepository movieScreeningRepository;
    private final AppUserRepository appUserRepository;

    public TicketResponseDto addTicket(CreateTicketDto dto){

        Seat seat = seatRepository.findById(dto.seatId()).orElseThrow();
        MovieScreening movieScreening = (movieScreeningRepository.findById(dto.movieScreeningId()).orElseThrow());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        AppUser user = appUserRepository.findByEmail(username).orElseThrow();

        Ticket ticket = new Ticket();

        ticket.setReserved(true);
        ticket.setSeat(seat);
        ticket.setMovieScreening(movieScreening);
        ticket.setCreatedAt(java.time.LocalDateTime.now());
        ticket.setPrice(movieScreening.getPrice());
        ticket.setUser(user);

        Ticket saved = ticketRepository.save(ticket);

        return new TicketResponseDto(
                saved.getId(),
                saved.getPrice(),
                saved.getMovieScreening().getId(),
                saved.getSeat().getId()
        );
    }
}
