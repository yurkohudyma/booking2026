package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.domain.Booking;

@Service
@RequiredArgsConstructor
@Log4j2
public class RefundService {
    //private final BookingService bookingService;

    public void refundBookingPayment(Booking booking) {
        var refundable = booking.getCost();
        booking.setRefunded(refundable);
    }
}
