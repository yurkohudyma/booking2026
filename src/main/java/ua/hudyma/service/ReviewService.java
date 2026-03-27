package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.Review;
import ua.hudyma.dto.ReviewReqDto;
import ua.hudyma.dto.ReviewRespDto;
import ua.hudyma.mapper.ReviewMapper;
import ua.hudyma.repository.ReviewRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final PropertyService propertyService;

    public ReviewRespDto createReview(ReviewReqDto dto) {
        var review = reviewMapper.toEntity(dto);
        reviewRepository.save(review);
        validateRatingCalculateAndUpdateProperty(dto.rating(), getBookingCode(review), getPropertyCode(review));
        return reviewMapper.toDto(review);
    }

    private String getPropertyCode(Review review) {
        return review.getBooking().getProperty().getPropertyCode();
    }

    private static String getBookingCode(Review review) {
        return review.getBooking().getBookingCode();
    }

    private void validateRatingCalculateAndUpdateProperty(Integer rating,
                                                          String bookingCode,
                                                          String propertyCode) {
        if (rating != null && (rating > 10 || rating <= 0)){
            log.warn(" ---> Rating " + rating + " for booking = " + bookingCode + "" +
                    " is OUT OF BOUNDS [1-10], not accountable");
            calculateAvgPropertyRating(propertyCode);
        }
    }

    @Transactional
    public BigDecimal calculateAvgPropertyRating(String propertyCode){
        var property = propertyService.getProperty(propertyCode);
        var ratingsList = property
                .getBookingList()
                .stream()
                .flatMap(booking -> booking.getReviewList().stream())
                .map(Review::getRating)
                .filter(rating -> rating > 0 && rating <= 10)
                .toList();
        var rating = getAvg(ratingsList);
        if (!rating.equals(BigDecimal.ZERO)) property.setRating(rating);
        return rating;
    }
    private BigDecimal getAvg(List<Integer> ratingsList) {
        return ratingsList
                .stream()
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(ratingsList.size()),
                        2, RoundingMode.HALF_UP);
    }
}
