package ua.hudyma.dto;

import java.math.BigDecimal;

public record Geolocation(
        BigDecimal latitude,
        BigDecimal longitude
) {

}
