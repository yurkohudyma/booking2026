package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.TransactionReqDto;
import ua.hudyma.dto.TransactionRespDto;
import ua.hudyma.service.TransactionService;

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




}
