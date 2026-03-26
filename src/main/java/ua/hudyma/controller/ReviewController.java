package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hudyma.dto.BookingReqDto;
import ua.hudyma.dto.BookingRespDto;
import ua.hudyma.dto.ReviewReqDto;
import ua.hudyma.dto.ReviewRespDto;
import ua.hudyma.service.BookingService;
import ua.hudyma.service.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewRespDto> createReview (
            @RequestBody ReviewReqDto dto){
        return ResponseEntity.ok(reviewService
                .createReview(dto));
    }
}
