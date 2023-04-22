package com.jolly.corebankingservice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jolly
 */
@Getter
@Setter
@Entity
@Table(name = "banking_core_utility_account")
public class UtilityAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String number;
    private String providerName;

}
