package ua.hudyma.dto;

import java.time.LocalDate;

public record BookingReqDto(
        String userId,
        String propertyCode,
        String roomCode,
        LocalDate start,
        LocalDate finish,
        Integer visitorsCount
) {}
