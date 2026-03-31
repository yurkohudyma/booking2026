package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.Transaction;
import ua.hudyma.dto.TransactionReqDto;
import ua.hudyma.dto.TransactionRespDto;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TransactionMapper extends BaseMapper<
        TransactionRespDto, Transaction, TransactionReqDto> {

    @Override
    public TransactionRespDto toDto(Transaction transaction) {
        return new TransactionRespDto(
                transaction.getTransactionId(),
                transaction.getUser().getName(),
                transaction.getTransactionStatus(),
                transaction.getBooking().getCost(),
                transaction.getAmount()
        );
    }
    @Override
    public Transaction toEntity(TransactionReqDto dto) {
        var transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setAmount(dto.amount());
        return transaction;
    }

}
