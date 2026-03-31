package ua.hudyma.dto;

import ua.hudyma.enums.TransactionStatus;

import java.math.BigDecimal;

public record TransactionRespDto(
        String transactionId,
        String userName,
        TransactionStatus transactionStatus,
        BigDecimal bookingCost,
        BigDecimal txAmount
) {

}
