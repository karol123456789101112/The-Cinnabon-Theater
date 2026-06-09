package org.example.service;

import org.example.Dto.ReservationScreeningDto;

public interface ReservationService {
    ReservationScreeningDto getReservationInfo(long id);
}
