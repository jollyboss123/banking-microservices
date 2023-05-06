package com.jolly.fundtransferservice.controller;

import com.jolly.fundtransferservice.model.dto.FundTransfer;
import com.jolly.fundtransferservice.model.dto.FundTransferRequest;
import com.jolly.fundtransferservice.model.dto.FundTransferResponse;
import com.jolly.fundtransferservice.service.FundTransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jolly
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/transfer")
public class FundTransferController {
    private final FundTransferService fundTransferService;

    @PostMapping
    public ResponseEntity<FundTransferResponse> sendFundTransfer(@RequestBody FundTransferRequest request) {
        log.info("Got fund transfer request: {}", request.toString());

        return ResponseEntity.ok(fundTransferService.fundTransfer(request));
    }

    @GetMapping
    public ResponseEntity<List<FundTransfer>> readFundTransfers(Pageable pageable) {
        log.info("Reading fund transfers from core");

        return ResponseEntity.ok(fundTransferService.readAllFundTransfers(pageable));
    }
}
