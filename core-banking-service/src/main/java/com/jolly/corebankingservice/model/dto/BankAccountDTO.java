package com.jolly.corebankingservice.model.dto;

import com.jolly.corebankingservice.model.AccountStatus;
import com.jolly.corebankingservice.model.AccountType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jolly
 */
@Data
public class BankAccountDTO {
    private Long id;
    private String number;
    private AccountType type;
    private AccountStatus status;
    private BigDecimal availableBalance;
    private BigDecimal actualBalance;
    private UserDTO user;
}
