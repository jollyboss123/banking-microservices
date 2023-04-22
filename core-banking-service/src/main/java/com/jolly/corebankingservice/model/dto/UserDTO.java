package com.jolly.corebankingservice.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author jolly
 */
@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String identificationNumber;
    private List<BankAccountDTO> bankAccounts;
}
