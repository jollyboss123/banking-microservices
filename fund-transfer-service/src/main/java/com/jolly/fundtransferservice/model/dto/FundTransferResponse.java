package com.jolly.fundtransferservice.model.dto;

import lombok.Data;

/**
 * @author jolly
 */
@Data
public class FundTransferResponse {
    private String message;
    private String transactionId;
}
