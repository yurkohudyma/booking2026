package ua.hudyma.dto;

import java.math.BigDecimal;

public record RoomReqDto(
        String propertyCode,
        Integer maxVisitors,
        BigDecimal cost
) {

}
