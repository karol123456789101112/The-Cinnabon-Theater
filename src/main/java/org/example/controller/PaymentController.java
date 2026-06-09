package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.Dto.CreateTicketDto;
import org.example.Dto.TicketResponseDto;
import org.example.domain.Ticket;
import org.example.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tickets")
public class PaymentController {

    private final TicketService ticketService;

    @PostMapping("/addTicket")
    public ResponseEntity<TicketResponseDto> addTicket(@RequestBody CreateTicketDto createTicketDto) {
        return ResponseEntity.ok(ticketService.addTicket(createTicketDto));
    }
}
