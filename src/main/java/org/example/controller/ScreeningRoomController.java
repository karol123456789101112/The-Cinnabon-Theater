package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.Dto.ScreeningRoomDto;
import org.example.service.ScreeningRoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/screeningRooms")
public class ScreeningRoomController {

    private final ScreeningRoomService screeningRoomService;

    @GetMapping
    public List<ScreeningRoomDto> getAllScreeningRooms() {
        return screeningRoomService.getAllScreeningRooms();
    }

}
