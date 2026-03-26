package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.Booking;
import ua.hudyma.domain.Review;
import ua.hudyma.dto.ReviewReqDto;
import ua.hudyma.dto.ReviewRespDto;
import ua.hudyma.service.BookingService;
import ua.hudyma.service.UserService;

@Component
@RequiredArgsConstructor
public class ReviewMapper extends BaseMapper<ReviewRespDto, Review, ReviewReqDto>{
    private final BookingService bookingService;
    private final UserService userService;

    @Override
    public ReviewRespDto toDto(Review review) {
        return new ReviewRespDto(
                review.getUser().getName(),
                review.getBooking().getBookingCode(),
                review.getBooking().getProperty().getName(),
                review.getRating(),
                review.getDetails()
        );
    }
    @Override
    public Review toEntity(ReviewReqDto reviewReqDto) {
        var review = new Review();
        var booking = bookingService
                .getBooking(reviewReqDto.bookingCode());
        var user = booking.getUser();
        review.setBooking(booking);
        review.setUser(user);
        review.setRating(reviewReqDto.rating());
        review.setDetails(reviewReqDto.details());
        return review;
    }

}
