package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.Booking;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingCode(String bookingCode);

}
