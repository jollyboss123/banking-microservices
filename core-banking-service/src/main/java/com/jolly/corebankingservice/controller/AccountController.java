package com.jolly.corebankingservice.controller;

import com.jolly.corebankingservice.model.dto.BankAccountDTO;
import com.jolly.corebankingservice.model.dto.UtilityAccountDTO;
import com.jolly.corebankingservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jolly
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/bank-account/{account_number}")
    public ResponseEntity<BankAccountDTO> getBankAccount(@PathVariable("account_number") String accountNumber) {
        log.info("Reading account with ID {}", accountNumber);
        return ResponseEntity.ok(accountService.readBankAccount(accountNumber));
    }

    @GetMapping("/util-account/{account_name}")
    public ResponseEntity<UtilityAccountDTO> getUtilityAccount(@PathVariable("account_name") String providerName) {
        log.info("Reading utility account with ID {}", providerName);
        return ResponseEntity.ok(accountService.readUtilityAccount(providerName));
    }
}
