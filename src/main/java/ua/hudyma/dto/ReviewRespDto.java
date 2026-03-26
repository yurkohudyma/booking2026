package ua.hudyma.dto;

public record ReviewRespDto(
        String visitorName,
        String bookingCode,
        String propertyName,
        Integer rating,
        String details
) {

}
