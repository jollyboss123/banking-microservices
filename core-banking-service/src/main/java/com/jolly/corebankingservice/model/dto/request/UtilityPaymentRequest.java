package com.jolly.corebankingservice.model.dto.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jolly
 */
@Data
public class UtilityPaymentRequest {
    private Long providerId;
    private BigDecimal amount;
    private String referenceNumber;
    private String account;
}
