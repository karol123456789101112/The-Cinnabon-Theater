package org.example.service;

import org.example.Dto.CreateTicketDto;
import org.example.Dto.TicketResponseDto;

public interface TicketService {
    TicketResponseDto addTicket(CreateTicketDto dto);
}
