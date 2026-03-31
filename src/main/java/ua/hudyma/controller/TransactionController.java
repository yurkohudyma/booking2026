package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.domain.Booking;
import ua.hudyma.dto.TransactionReqDto;
import ua.hudyma.dto.TransactionRespDto;
import ua.hudyma.service.TransactionService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionRespDto> createTransaction (
            @RequestBody TransactionReqDto dto){
        return ResponseEntity.ok(transactionService.createTransaction(dto));
    }

    @GetMapping("/debt")
    public ResponseEntity<BigDecimal> retrieveDebts (
            @RequestParam String bookingCode){
        return ResponseEntity.ok(transactionService.retrieveDebts(bookingCode));
    }

    @GetMapping
    public ResponseEntity<List<TransactionRespDto>> getAllBookingTx (
            @RequestParam String bookingCode){
        return ResponseEntity.ok(transactionService.getAllTx(bookingCode));
    }




}
