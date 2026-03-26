package ua.hudyma.dto;

public record ReviewReqDto(
        String bookingCode,
        Integer rating,
        String details
) {

}
