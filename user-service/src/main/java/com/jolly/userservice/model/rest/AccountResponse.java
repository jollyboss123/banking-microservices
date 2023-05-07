package com.jolly.userservice.model.rest;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author jolly
 */
@Getter
@Setter
public class AccountResponse {
    private String number;
    private BigDecimal actualBalance;
    private Integer id;
    private String type;
    private String status;
    private BigDecimal availableBalance;
}
