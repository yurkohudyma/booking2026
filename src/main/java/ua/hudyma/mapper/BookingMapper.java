package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.Booking;
import ua.hudyma.dto.BookingReqDto;
import ua.hudyma.dto.BookingRespDto;
import ua.hudyma.service.PropertyService;
import ua.hudyma.service.RoomService;

@Component
@RequiredArgsConstructor
public class BookingMapper extends BaseMapper<BookingRespDto, Booking, BookingReqDto> {

    private final RoomService roomService;
    private final PropertyService propertyService;

    @Override
    public BookingRespDto toDto(Booking booking) {
        return null;
    }

    @Override
    public Booking toEntity(BookingReqDto dto) {

        var booking = new Booking();

        return null;
    }

}
