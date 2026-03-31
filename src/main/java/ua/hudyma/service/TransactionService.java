package ua.hudyma.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.domain.Booking;
import ua.hudyma.dto.TransactionReqDto;
import ua.hudyma.dto.TransactionRespDto;
import ua.hudyma.enums.BookingStatus;
import ua.hudyma.mapper.TransactionMapper;
import ua.hudyma.repository.TransactionRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Log4j2
public class TransactionService {
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final BookingService bookingService;

    @Transactional
    public TransactionRespDto createTransaction(TransactionReqDto dto) {
        var booking = bookingService.getBooking(dto.bookingId());
        var user = booking.getUser();
        var transaction = transactionMapper.toEntity(dto);
        validateBookingPayment(booking, dto.amount());
        transaction.setUser(user);
        transaction.setBooking(booking);
        transactionRepository.save(transaction);
        booking.getTransactionList().add(transaction);
        return transactionMapper.toDto(transaction);
    }

    private static void validateBookingPayment(
            Booking booking, BigDecimal txAmount) {
        var bookingAmount = booking.getCost();
        if (txAmount == null || bookingAmount == null) {
            throw new IllegalArgumentException
                    ("Booking amount or TxAmount are null");
        }
        if (txAmount.compareTo(bookingAmount) >= 0){
            booking.setBookingStatus(BookingStatus.PAID);
            log.info(" ---> Booking payment has been provided in full");
        }
        else {
            log.warn(" ---> Booking payment has NOT been fully paid, {} is left to pay",
                    bookingAmount.subtract(txAmount));
        }
    }
}
