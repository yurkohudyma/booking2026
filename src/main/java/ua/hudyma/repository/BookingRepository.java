package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
