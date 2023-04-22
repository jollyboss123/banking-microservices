package com.jolly.corebankingservice.service;

import com.jolly.corebankingservice.model.dto.BankAccountDTO;
import com.jolly.corebankingservice.model.dto.UtilityAccountDTO;
import com.jolly.corebankingservice.model.entity.BankAccountEntity;
import com.jolly.corebankingservice.model.entity.UtilityAccountEntity;
import com.jolly.corebankingservice.model.mapper.BankAccountMapper;
import com.jolly.corebankingservice.model.mapper.UtilityAccountMapper;
import com.jolly.corebankingservice.repository.BankAccountRepository;
import com.jolly.corebankingservice.repository.UtilityAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author jolly
 */
@Service
@RequiredArgsConstructor
public class AccountService {
    private final BankAccountMapper bankAccountMapper = new BankAccountMapper();
    private final UtilityAccountMapper utilityAccountMapper = new UtilityAccountMapper();
    private final BankAccountRepository bankAccountRepository;
    private final UtilityAccountRepository utilityAccountRepository;

    public BankAccountDTO readBankAccount(String accountNumber) {
        BankAccountEntity bankAccountEntity = bankAccountRepository
                .findByAccountNumber(accountNumber)
                .orElse(new BankAccountEntity());
        return bankAccountMapper.convertToDto(bankAccountEntity);
    }

    public UtilityAccountDTO readUtilityAccount(String provider) {
        UtilityAccountEntity utilityAccountEntity = utilityAccountRepository
                .findByProviderName(provider)
                .orElse(new UtilityAccountEntity());
        return utilityAccountMapper.convertToDto(utilityAccountEntity);
    }

    public UtilityAccountDTO readUtilityAccount(Long id) {
        UtilityAccountEntity utilityAccountEntity = utilityAccountRepository
                .findById(id)
                .orElse(new UtilityAccountEntity());
        return utilityAccountMapper.convertToDto(utilityAccountEntity);
    }
}
