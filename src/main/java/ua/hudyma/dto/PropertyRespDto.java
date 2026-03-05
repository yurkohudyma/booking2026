package ua.hudyma.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PropertyRespDto(
        Long id,
        String propertyId,
        String description,
        String address,
        Geolocation geolocation,
        BigDecimal rating,
        LocalDateTime registeredOn,
        LocalDateTime renewedOn,
        String name) {
}
