package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.BookingReqDto;
import ua.hudyma.dto.BookingRespDto;
import ua.hudyma.service.BookingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingRespDto>
    createBooking (
            @RequestBody BookingReqDto dto){
        return ResponseEntity.ok(bookingService
                .createBooking(dto));
    }

    @PatchMapping("/cancel")
    public ResponseEntity<BookingRespDto>
    cancelBookings(
            @RequestParam String bookingCode){
        return ResponseEntity.ok(bookingService
                .cancelBooking(bookingCode));
    }
}
