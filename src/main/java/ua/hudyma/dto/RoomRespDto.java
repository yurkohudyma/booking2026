package ua.hudyma.dto;

import java.math.BigDecimal;

public record RoomRespDto(
        Long roomId,
        String roomCode,
        BigDecimal roomCost,
        String propertyCode


) {

}
