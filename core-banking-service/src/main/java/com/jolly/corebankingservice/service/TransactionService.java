package com.jolly.corebankingservice.service;

import com.jolly.corebankingservice.model.TransactionType;
import com.jolly.corebankingservice.model.dto.BankAccountDTO;
import com.jolly.corebankingservice.model.dto.UtilityAccountDTO;
import com.jolly.corebankingservice.model.dto.request.FundTransferRequest;
import com.jolly.corebankingservice.model.dto.request.UtilityPaymentRequest;
import com.jolly.corebankingservice.model.dto.response.FundTransferResponse;
import com.jolly.corebankingservice.model.dto.response.UtilityPaymentResponse;
import com.jolly.corebankingservice.model.entity.BankAccountEntity;
import com.jolly.corebankingservice.model.entity.TransactionEntity;
import com.jolly.corebankingservice.repository.BankAccountRepository;
import com.jolly.corebankingservice.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author jolly
 */

//TODO: improve this with state machine
@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {
    private final AccountService accountService;
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    public FundTransferResponse fundTransfer(FundTransferRequest request) {
        BankAccountDTO fromBankAccount = accountService.readBankAccount(request.getFromAccount());
        BankAccountDTO toBankAccount = accountService.readBankAccount(request.getToAccount());

        validateBalance(fromBankAccount, request.getAmount());

        String transactionId = internalFundTransfer(fromBankAccount, toBankAccount, request.getAmount());
        return FundTransferResponse.builder()
                .message("Transaction successfully completed")
                .transactionId(transactionId)
                .build();
    }

    public UtilityPaymentResponse utilityPayment(UtilityPaymentRequest request) {
        String transactionId = UUID.randomUUID().toString();

        BankAccountDTO fromBankAccount = accountService.readBankAccount(request.getAccount());

        validateBalance(fromBankAccount, request.getAmount());

        BankAccountEntity fromBankAccountEntity = bankAccountRepository
                .findByAccountNumber(fromBankAccount.getNumber())
                .orElseThrow(() -> new RuntimeException("Source Bank Account not found"));

        //TODO: call 3rd party API to process Utility Payment from payment provider here
        UtilityAccountDTO utilityAccountDTO = accountService.readUtilityAccount(request.getProviderId());

        fromBankAccountEntity.setActualBalance(
                fromBankAccountEntity.getActualBalance().subtract(request.getAmount())
        );
        fromBankAccountEntity.setAvailableBalance(
                fromBankAccountEntity.getAvailableBalance().subtract(request.getAmount())
        );

        transactionRepository.save(TransactionEntity.builder()
                .transactionType(TransactionType.UTILITY_PAYMENT)
                .account(fromBankAccountEntity)
                .transactionId(transactionId)
                .referenceNumber(request.getReferenceNumber())
                .amount(request.getAmount().negate())
                .build());

        return UtilityPaymentResponse.builder()
                .message("Utility Payment successfully completed")
                .transactionId(transactionId)
                .build();
    }

    private void validateBalance(BankAccountDTO bankAccountDTO, BigDecimal amount) {
        if (bankAccountDTO.getActualBalance().compareTo(BigDecimal.ZERO) < 0 ||
        bankAccountDTO.getActualBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Balance in bank account insufficient for transaction");
        }
    }

    private String internalFundTransfer(BankAccountDTO fromBankAccount,
                                       BankAccountDTO toBankAccount,
                                       BigDecimal amount) {
        String transactionId = UUID.randomUUID().toString();

        BankAccountEntity fromBankAccountEntity = bankAccountRepository
                .findByAccountNumber(fromBankAccount.getNumber())
                .orElseThrow(() -> new RuntimeException("Source Bank account not found"));

        BankAccountEntity toBankAccountEntity = bankAccountRepository
                .findByAccountNumber(toBankAccount.getNumber())
                .orElseThrow(() -> new RuntimeException("Target Bank account not found"));

        fromBankAccountEntity.setActualBalance(
                fromBankAccountEntity.getActualBalance().subtract(amount)
        );
        fromBankAccountEntity.setAvailableBalance(
                fromBankAccountEntity.getAvailableBalance().subtract(amount)
        );
        bankAccountRepository.save(fromBankAccountEntity);

        transactionRepository.save(TransactionEntity.builder()
                        .transactionType(TransactionType.FUND_TRANSFER)
                        .referenceNumber(toBankAccountEntity.getNumber())
                        .transactionId(transactionId)
                        .account(fromBankAccountEntity)
                        .amount(amount.negate())
                        .build());

        toBankAccountEntity.setActualBalance(
                toBankAccountEntity.getActualBalance().add(amount)
        );
        toBankAccountEntity.setAvailableBalance(
                toBankAccountEntity.getAvailableBalance().add(amount)
        );
        bankAccountRepository.save(toBankAccountEntity);

        transactionRepository.save(TransactionEntity.builder()
                        .transactionType(TransactionType.FUND_TRANSFER)
                        .referenceNumber(toBankAccountEntity.getNumber())
                        .transactionId(transactionId)
                        .account(toBankAccountEntity)
                        .amount(amount)
                        .build());

        return transactionId;
    }
}
