package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.Property;
import ua.hudyma.domain.Room;
import ua.hudyma.dto.RoomReqDto;
import ua.hudyma.dto.RoomRespDto;
import ua.hudyma.mapper.RoomMapper;
import ua.hudyma.repository.RoomRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Log4j2
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final PropertyService propertyService;

    public Room getRoom(String roomCode) {
        return roomRepository.findByRoomCode(roomCode)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Room " + roomCode + " NOT found")
                );
    }

    public BigDecimal getRoomCost(String roomCode) {
        return getRoom(roomCode).getCost();
    }

    @Transactional
    public RoomRespDto createRoom(RoomReqDto dto) {
        var room = roomMapper.toEntity(dto);
        roomRepository.save(room);
        var property = propertyService.getProperty(dto.propertyCode());
        property.getRoomList().add(room);
        room.setProperty(property);
        return roomMapper.toDto(room);
    }
}
