package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.domain.Booking;
import ua.hudyma.domain.Room;
import ua.hudyma.dto.BookingReqDto;
import ua.hudyma.dto.BookingRespDto;
import ua.hudyma.mapper.BookingMapper;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookingService {
    private final BookingMapper bookingMapper;
    private final PropertyService propertyService;
    private final RoomService roomService;

    public BookingRespDto createBooking (BookingReqDto dto){
        var property = propertyService.getProperty(dto.propertyId());
        var room = roomService.getRoom(dto.roomId());
        checkRoomAvailability(room, dto);
        var booking = new Booking();
        return bookingMapper.toDto(booking);
    }

    private static void checkRoomAvailability(Room room, BookingReqDto dto) {
        throw new IllegalStateException("Method not implemented");
    }

}
