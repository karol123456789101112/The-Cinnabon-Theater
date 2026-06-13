package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Dto.CreateTicketDto;
import org.example.Dto.TicketResponseDto;
import org.example.Dto.TicketUserResponseDto;
import org.example.domain.*;
import org.example.repository.AppUserRepository;
import org.example.repository.MovieScreeningRepository;
import org.example.repository.SeatRepository;
import org.example.repository.TicketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                saved.getSeat().getId(),
                saved.getUser().getId()
        );
    }

    public List<TicketResponseDto> getAllActiveTickets() {
        return ticketRepository.findByReservedTrueOrderByIdAsc()
                .stream()
                .map(t -> new TicketResponseDto(
                        t.getId(),
                        t.getPrice(),
                        t.getSeat().getId(),
                        t.getMovieScreening().getId(),
                        t.getUser().getId()
                ))
                .toList();
    }

    @Transactional
    public void cancelTicket(long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        ticket.setReserved(false);
    }

    public List<TicketUserResponseDto> getAllUserTickets(String email) {
        AppUser user = appUserRepository.findByEmail(email).orElseThrow();

        return ticketRepository.findByUserIdOrderByMovieScreeningStartTimeDesc(user.getId())
                .stream()
                .map(t -> new TicketUserResponseDto(
                        t.getId(),
                        t.getCreatedAt(),
                        t.getPrice(),
                        t.getMovieScreening().getStartTime(),
                        t.getMovieScreening().getMovie().getName(),
                        t.getSeat().getNumber(),
                        t.getSeat().getRow(),
                        t.getMovieScreening().getScreeningRoom().getRoomNumber(),
                        t.getMovieScreening().getMovie().getDuration(),
                        t.isReserved(),
                        user.getEmail()
                ))
                .toList();
    }
}
