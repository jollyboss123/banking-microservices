package com.jolly.corebankingservice.model.entity;

import com.jolly.corebankingservice.model.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author jolly
 */
@Getter
@Setter
@Entity
@Table(name = "banking_core_transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String referenceNumber;
    private String transactionId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private BankAccountEntity account;

}
