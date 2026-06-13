package org.example.service;

import org.example.Dto.CreateTicketDto;
import org.example.Dto.TicketResponseDto;
import org.example.Dto.TicketUserResponseDto;

import java.util.List;

public interface TicketService {
    TicketResponseDto addTicket(CreateTicketDto dto);
    List<TicketResponseDto> getAllActiveTickets();
    void cancelTicket(long id);
    List<TicketUserResponseDto> getAllUserTickets(String email);
}
