package com.jolly.corebankingservice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author jolly
 */

/**
 * User base
 * multiple bank accounts per user
 */
@Getter
@Setter
@Entity
@Table(name = "banking_core_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String identificationNumber;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BankAccountEntity> accounts;

}
