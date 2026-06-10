package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Dto.ReservationScreeningDto;
import org.example.Dto.SeatDto;
import org.example.domain.MovieScreening;
import org.example.domain.Seat;
import org.example.domain.Ticket;
import org.example.repository.MovieScreeningRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final MovieScreeningRepository movieScreeningRepository;

    @Transactional
    public ReservationScreeningDto getReservationInfo(long id){

        MovieScreening movieScreening = movieScreeningRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MovieScreening not found: " + id));

        List<SeatDto> seats = new ArrayList<>();
        List<Long> reservedSeatsIds = new ArrayList<>();

        for(Ticket ticket : movieScreening.getTickets()){
            if(ticket.isReserved()){
                reservedSeatsIds.add(ticket.getSeat().getId());
            }
        }

        for(Seat seat : movieScreening.getScreeningRoom().getSeats()){

            boolean reserved = reservedSeatsIds.contains(seat.getId());

            SeatDto seatDto = new SeatDto(
                    seat.getId(),
                    seat.getRow(),
                    seat.getNumber(),
                    reserved
            );

            seats.add(seatDto);
        }

        List<String> genres = movieScreening.getMovie()
                .getGenres()
                .stream()
                .map(g -> g.getName())
                .toList();

        ReservationScreeningDto dto = new ReservationScreeningDto(
                movieScreening.getStartTime().toLocalDate(),
                movieScreening.getMovie().getName(),
                movieScreening.getMovie().getDuration(),
                movieScreening.getStartTime().toLocalTime(),
                movieScreening.getScreeningRoom().getRoomNumber(),
                movieScreening.getPrice(),
                genres,
                List.copyOf(seats)
        );

        return dto;
    }
}
