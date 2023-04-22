package com.jolly.corebankingservice.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jolly
 */
@Data
public class TransactionDTO {
    private Long id;
    private BigDecimal amount;
    private BankAccountDTO bankAccount;
    private String referenceNumber;
}
