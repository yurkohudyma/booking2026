package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.Booking;
import ua.hudyma.domain.Room;
import ua.hudyma.dto.BookingReqDto;
import ua.hudyma.dto.BookingRespDto;
import ua.hudyma.mapper.BookingMapper;
import ua.hudyma.repository.BookingRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookingService {

    private final BookingMapper bookingMapper;

    private final BookingRepository bookingRepository;

    private final PropertyService propertyService;

    private final RoomService roomService;

    private final UserService userService;

    @Transactional
    public BookingRespDto createBooking(BookingReqDto dto) {
        var property = propertyService.getProperty(dto.propertyCode());
        var user = userService.getUser(dto.userId());
        var room = roomService.getRoom(dto.roomCode());
        if (!property.getRoomList().contains(room)){
            throw new IllegalArgumentException("Requested room " + room.getRoomCode() +
                    " does NOT belong to property " + property.getName());
        }
        var maxCapacity = room.getMaxVisitorsCapacity();
        checkCapacity(dto, maxCapacity);
        checkRoomAvailability(room, dto.start());
        var visitorsCount = dto.visitorsCount();
        var roomCost = room.getCost();
        var booking = bookingMapper.toEntity(dto);
        var finalPrice = roomCost.multiply(BigDecimal
                .valueOf(visitorsCount * calculationBookingDuration
                        (booking.getStart(), booking.getFinish())));
        booking.setCost(finalPrice);
        booking.setAdditionalVisitorsCount(visitorsCount);
        booking.setUser(user);
        booking.setProperty(property);
        booking.setRoom(room);
        bookingRepository.save(booking);
        user.getBookingList().add(booking);
        property.getBookingList().add(booking);
        room.getBookingList().add(booking);
        return bookingMapper.toDto(booking);
    }

    private static Long calculationBookingDuration(LocalDate start, LocalDate finish) {
        return ChronoUnit.DAYS.between(start, finish);
    }

    private static void checkCapacity(BookingReqDto dto, Integer maxCapacity) {
        if (maxCapacity < dto.visitorsCount()) {
            throw new IllegalArgumentException("Insufficient visitors: " +
                    "requested " + dto.visitorsCount() + ", while available " +
                    maxCapacity);
        }
    }

    private static void checkRoomAvailability(Room room, LocalDate start) {
        Predicate<Booking> dateIsOverbooked = booking ->
                booking.getStart().isBefore(start) ||
                        booking.getStart().isEqual(start);
        if (room.getBookingList()
                .stream()
                .anyMatch(dateIsOverbooked))
            throw new IllegalArgumentException("Room " + room.getRoomCode() + " is already booked on " + start
            );
        //in real frontend logic, there would be no necessity to check room availability
        //as client would have been proposed only vacant ones
    }
}
