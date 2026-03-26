package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.BookingReqDto;
import ua.hudyma.dto.BookingRespDto;
import ua.hudyma.dto.ReviewReqDto;
import ua.hudyma.dto.ReviewRespDto;
import ua.hudyma.service.BookingService;
import ua.hudyma.service.ReviewService;

import java.math.BigDecimal;

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

    @GetMapping("/getAverageRating")
    public ResponseEntity<BigDecimal> calculateAvgPropertyRating (
            @RequestParam String propertyCode){
        return ResponseEntity.ok(reviewService
                .calculateAvgPropertyRating(propertyCode));
    }
}
