package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.domain.Room;
import ua.hudyma.mapper.RoomMapper;
import ua.hudyma.repository.RoomRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public Room getRoom(String roomId) {
        return roomRepository.findByRoomId(roomId)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Room " + roomId + " NOT found")
                );

    }

}
