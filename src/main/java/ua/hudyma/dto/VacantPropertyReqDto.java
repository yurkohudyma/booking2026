package ua.hudyma.dto;

import java.time.LocalDate;

public record VacantPropertyReqDto(
        String city,
        LocalDate start,
        LocalDate finish
) {

}
