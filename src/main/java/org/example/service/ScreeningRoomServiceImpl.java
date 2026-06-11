package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Dto.ScreeningRoomDto;
import org.example.repository.ScreeningRoomRepository;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningRoomServiceImpl implements ScreeningRoomService{

    private final ScreeningRoomRepository screeningRoomRepository;

    public List<ScreeningRoomDto> getAllScreeningRooms() {
        return screeningRoomRepository.findAll()
                .stream()
                .map(sr -> new ScreeningRoomDto(sr.getId(), sr.getRoomNumber()))
                .toList();
    }
}
