package com.jolly.fundtransferservice.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jolly
 */
@Data
public class FundTransfer {
    private Long id;
    private String transactionReference;
    private String status;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
}
