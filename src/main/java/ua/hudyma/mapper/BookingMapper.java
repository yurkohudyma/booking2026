package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.Booking;
import ua.hudyma.domain.User;
import ua.hudyma.dto.BookingReqDto;
import ua.hudyma.dto.BookingRespDto;
import ua.hudyma.enums.BookingStatus;
import ua.hudyma.service.PropertyService;
import ua.hudyma.service.RoomService;
import ua.hudyma.service.UserService;

import static ua.hudyma.enums.BookingStatus.CONFIRMED;

@Component
@RequiredArgsConstructor
public class BookingMapper extends BaseMapper<BookingRespDto, Booking, BookingReqDto> {

    private final RoomService roomService;

    @Override
    public BookingRespDto toDto(Booking booking) {
        return new BookingRespDto(
                booking.getId(),
                booking.getBookingCode(),
                booking.getCreatedOn(),
                booking.getStart(),
                booking.getFinish(),
                booking.getUser().getName(),
                booking.getProperty().getName(),
                booking.getRoom().getRoomCode(),
                booking.getAdditionalVisitorsCount(),
                booking.getBookingStatus(),
                booking.getCost()
        );
    }

    @Override
    public Booking toEntity(BookingReqDto dto) {
        var booking = new Booking();
        booking.setBookingStatus(CONFIRMED);
        booking.setCost(roomService
                .getRoomCost(dto.roomCode()));
        if (dto.start() != null) booking.setStart(dto.start());
        booking.setFinish(dto.finish());
        return booking;
    }
}
