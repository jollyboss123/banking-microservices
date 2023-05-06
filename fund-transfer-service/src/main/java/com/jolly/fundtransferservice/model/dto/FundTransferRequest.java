package com.jolly.fundtransferservice.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jolly
 */
@Data
public class FundTransferRequest {
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private String authId;
}
