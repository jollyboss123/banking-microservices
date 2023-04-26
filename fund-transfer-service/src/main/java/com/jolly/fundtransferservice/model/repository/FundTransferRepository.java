package com.jolly.fundtransferservice.model.repository;

import com.jolly.fundtransferservice.model.entity.FundTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jolly
 */
public interface FundTransferRepository extends JpaRepository<FundTransferEntity, Long> {
}
