package ua.hudyma.dto;

import java.math.BigDecimal;

public record TransactionReqDto(
        String bookingId,
        BigDecimal amount
) {

}
