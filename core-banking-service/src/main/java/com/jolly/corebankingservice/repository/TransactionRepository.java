package com.jolly.corebankingservice.repository;

import com.jolly.corebankingservice.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jolly
 */
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
