package ua.hudyma.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BookingReqDto(
        String userId,
        String propertyId,
        String roomId,
        LocalDate start,
        LocalDate finish,
        Integer visitorsCount
) {

}
