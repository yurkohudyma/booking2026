package ua.hudyma.dto;

import ua.hudyma.enums.PropertyType;

public record PropertyReqDto(
        String name,
        String description,
        PropertyType propertyType,
        String ownerId,
        String address,
        Geolocation geolocation

) {

}
