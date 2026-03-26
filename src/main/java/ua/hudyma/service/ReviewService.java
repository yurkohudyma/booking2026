package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.dto.ReviewReqDto;
import ua.hudyma.dto.ReviewRespDto;
import ua.hudyma.mapper.ReviewMapper;
import ua.hudyma.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewRespDto createReview(ReviewReqDto dto) {
        var review = reviewMapper.toEntity(dto);
        reviewRepository.save(review);
        return reviewMapper.toDto(review);
    }

    //todo calculate median rating from existing reviews
    // for property and update

}
