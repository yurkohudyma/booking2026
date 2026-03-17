package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.Room;
import ua.hudyma.dto.RoomReqDto;
import ua.hudyma.dto.RoomRespDto;

@Component
public class RoomMapper extends BaseMapper<RoomRespDto, Room, RoomReqDto> {

    @Override
    public RoomRespDto toDto(Room room) {
        return new RoomRespDto(
                room.getId(),
                room.getRoomCode(),
                room.getCost(),
                room.getProperty().getName()
                );
    }

    @Override
    public Room toEntity(RoomReqDto dto) {
        var room = new Room();
        room.setCost(dto.cost());
        if (dto.maxVisitors() != null) {
            room.setMaxVisitorsCapacity(dto.maxVisitors());
        }
        return room;
    }

}
