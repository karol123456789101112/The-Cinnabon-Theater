package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.Dto.ReservationScreeningDto;
import org.example.service.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    public final ReservationService reservationService;

    @GetMapping("/{id}")
    public ReservationScreeningDto getReservationInfo(@PathVariable("id") long id){

        System.out.println("CONTROLLER");
        return reservationService.getReservationInfo(id);
    }
}
