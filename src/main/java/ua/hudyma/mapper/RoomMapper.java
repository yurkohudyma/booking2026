package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.Room;
import ua.hudyma.dto.RoomReqDto;
import ua.hudyma.dto.RoomRespDto;

@Component
public class RoomMapper extends BaseMapper<RoomRespDto, Room, RoomReqDto>{

    @Override
    public RoomRespDto toDto(Room room) {

        return null;
    }

    @Override
    public Room toEntity(RoomReqDto roomReqDto) {

        return null;
    }

}
