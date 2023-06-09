package com.jolly.corebankingservice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jolly
 */

/**
 * to store 3rd party utility payment numbers
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
