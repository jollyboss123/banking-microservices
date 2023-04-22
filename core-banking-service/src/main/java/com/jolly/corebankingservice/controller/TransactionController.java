package com.jolly.corebankingservice.controller;

import com.jolly.corebankingservice.model.dto.request.FundTransferRequest;
import com.jolly.corebankingservice.model.dto.request.UtilityPaymentRequest;
import com.jolly.corebankingservice.model.dto.response.FundTransferResponse;
import com.jolly.corebankingservice.model.dto.response.UtilityPaymentResponse;
import com.jolly.corebankingservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jolly
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/fund-transfer")
    public ResponseEntity<FundTransferResponse> fundTransfer(@RequestBody FundTransferRequest request) {
        log.info("Fund transfer initiated in core bank from {}", request.toString());
        return ResponseEntity.ok(transactionService.fundTransfer(request));
    }

    @PostMapping("/util-payment")
    public ResponseEntity<UtilityPaymentResponse> utilityPayment(@RequestBody UtilityPaymentRequest request) {
        log.info("Utility payment initiated in core bank from {}", request.toString());
        return ResponseEntity.ok(transactionService.utilityPayment(request));
    }
}
