package org.example.repository;

import org.example.domain.ScreeningRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRoomRepository extends JpaRepository<ScreeningRoom, Long> {
}
