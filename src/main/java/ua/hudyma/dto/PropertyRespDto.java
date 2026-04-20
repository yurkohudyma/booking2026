package ua.hudyma.dto;

import ua.hudyma.enums.PropertyType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PropertyRespDto(
        Long id,
        String name,
        String propertyId,
        String description,
        PropertyType propertyType,
        String address,
        Geolocation geolocation,
        Double distanceFromCenter,
        BigDecimal rating,
        LocalDateTime registeredOn,
        LocalDateTime renewedOn,
        String ownerName) {
}
