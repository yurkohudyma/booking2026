package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.Review;
import ua.hudyma.dto.ReviewReqDto;
import ua.hudyma.dto.ReviewRespDto;
import ua.hudyma.repository.ReviewRepository;
import ua.hudyma.service.BookingService;

@Component
@RequiredArgsConstructor
public class ReviewMapper extends BaseMapper<ReviewRespDto, Review, ReviewReqDto>{
    private final BookingService bookingService;
    private final ReviewRepository reviewRepository;

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
        var booking = bookingService
                .getBooking(reviewReqDto.bookingCode());
        var user = booking.getUser();
        throwIfReviewAlreadyExists(user.getUserId());
        var review = new Review();
        review.setBooking(booking);
        review.setUser(user);
        review.setRating(reviewReqDto.rating());
        review.setDetails(reviewReqDto.details());
        return review;
    }
    private void throwIfReviewAlreadyExists(String userId) {
        if (reviewRepository.existsByUser_UserId(userId)){
            throw new IllegalArgumentException("User " + userId +
                    " already published, repeating one not allowed");
        }
    }

}
