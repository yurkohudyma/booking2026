package ua.hudyma.dto;

import ua.hudyma.enums.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record BookingRespDto(
        Long id,
        String bookingCode,
        LocalDateTime createdOn,
        LocalDate start,
        LocalDate finish,
        String userName,
        String propertyName,
        String roomCode,
        Integer additVisitors,
        BookingStatus bookingStatus,
        BigDecimal cost
) {

}
