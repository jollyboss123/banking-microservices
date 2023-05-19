package com.jolly.corebankingservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author jolly
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleBankingGlobalException extends RuntimeException {
    private String code;
    private String message;

    public SimpleBankingGlobalException(String message) {
        super(message);
    }
}
