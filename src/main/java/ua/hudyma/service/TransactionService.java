package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.Booking;
import ua.hudyma.domain.Transaction;
import ua.hudyma.dto.TransactionReqDto;
import ua.hudyma.dto.TransactionRespDto;
import ua.hudyma.enums.BookingStatus;
import ua.hudyma.mapper.TransactionMapper;
import ua.hudyma.repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

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
        var debt = retrieveDebts(booking.getBookingCode());
        if (debt.equals(ZERO) || debt.compareTo(ZERO) < 0){
            throw new IllegalStateException
                    ("Payment declined, booking has been paid in full");
        }
        var user = booking.getUser();
        var transaction = transactionMapper.toEntity(dto);
        validateBookingPayment(booking, dto.amount());
        transaction.setUser(user);
        transaction.setBooking(booking);
        transactionRepository.save(transaction);
        booking.getTransactionList().add(transaction);
        return transactionMapper.toDto(transaction);
    }

    private void validateBookingPayment(
            Booking booking, BigDecimal txAmount) {
        var bookingAmount = booking.getCost();
        if (txAmount == null || bookingAmount == null) {
            throw new IllegalArgumentException
                    ("Booking amount or TxAmount are null");
        }
        var debt = retrieveDebts(booking.getBookingCode());
        if (debt.equals(ZERO)){
            booking.setBookingStatus(BookingStatus.PAID);
            log.info(" ---> Booking payment has been provided in full");
        }
        else {
            log.warn(" ---> Booking payment has NOT been fully paid, {} is left to pay",
                    debt);
        }
    }
    @Transactional(readOnly = true)
    public BigDecimal retrieveDebts(String bookingCode) {
        var booking = bookingService.getBooking(bookingCode);
        var transactionList = transactionRepository
                .findAllByBookingId(booking.getId());
        var overallSum = transactionList
                .stream()
                .map(Transaction::getAmount)
                .reduce(ZERO, BigDecimal::add);
        return booking.getCost().subtract(overallSum);
    }
    @Transactional(readOnly = true)
    public List<TransactionRespDto> getAllTx(String bookingCode) {
        var bookingId = bookingService.getBooking(bookingCode).getId();
        return transactionRepository
                .findAllByBookingId(bookingId)
                .stream()
                .map(transactionMapper::toDto)
                .toList();
    }

    public void refundBookingPayment(String bookingCode) {
    }

}
