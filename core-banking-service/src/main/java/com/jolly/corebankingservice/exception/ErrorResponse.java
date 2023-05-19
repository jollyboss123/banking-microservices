package com.jolly.corebankingservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jolly
 */
@Getter
@Setter
@Builder
public class ErrorResponse {
    private String code;
    private String message;
}
