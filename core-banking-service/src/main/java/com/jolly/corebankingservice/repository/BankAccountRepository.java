package com.jolly.corebankingservice.repository;

import com.jolly.corebankingservice.model.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author jolly
 */
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
    @Query("select b from BankAccountEntity b where b.number = :accountNumber")
    Optional<BankAccountEntity> findByAccountNumber(@Param("accountNumber") String accountNumber);
}
