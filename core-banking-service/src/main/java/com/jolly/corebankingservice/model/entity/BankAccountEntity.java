package com.jolly.corebankingservice.model.entity;

import com.jolly.corebankingservice.model.AccountStatus;
import com.jolly.corebankingservice.model.AccountType;
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
@Table(name = "bank_account_entity")
public class BankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String number;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private BigDecimal availableBalance;
    private BigDecimal actualBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
