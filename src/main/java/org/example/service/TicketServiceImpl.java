package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Dto.CreateTicketDto;
import org.example.Dto.TicketResponseDto;
import org.example.domain.MovieScreening;
import org.example.domain.Seat;
import org.example.domain.Ticket;
import org.example.repository.MovieScreeningRepository;
import org.example.repository.SeatRepository;
import org.example.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final MovieScreeningRepository movieScreeningRepository;

    public TicketResponseDto addTicket(CreateTicketDto dto){

        Ticket ticket = new Ticket();

        ticket.setReserved(true);

        Seat seat = seatRepository.findById(dto.seatId()).orElseThrow();
        MovieScreening movieScreening = (movieScreeningRepository.findById(dto.movieScreeningId()).orElseThrow());

        ticket.setSeat(seat);
        ticket.setMovieScreening(movieScreening);

        Ticket saved = ticketRepository.save(ticket);

        return new TicketResponseDto(
                saved.getId(),
                seat.getId(),
                movieScreening.getId()
        );
    }
}
