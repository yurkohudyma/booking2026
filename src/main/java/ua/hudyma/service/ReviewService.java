package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.Booking;
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
        return reviewMapper.toDto(review);
    }

    @Transactional
    public BigDecimal calculateAvgPropertyRating (String propertyCode){
        var property = propertyService.getProperty(propertyCode);
        var ratingsList = property
                .getBookingList()
                .stream()
                .flatMap(booking -> booking.getReviewList().stream())
                .map(Review::getRating)
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
                .divide(BigDecimal.valueOf(ratingsList.size())
                        , 2, RoundingMode.HALF_UP);
    }
}
