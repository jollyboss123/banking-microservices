package com.jolly.corebankingservice.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jolly
 */
@Builder
@Getter
@Setter
public class UtilityPaymentResponse {
    private String message;
    private String transactionId;
}
