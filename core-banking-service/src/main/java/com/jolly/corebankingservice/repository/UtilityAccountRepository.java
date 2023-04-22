package com.jolly.corebankingservice.repository;

import com.jolly.corebankingservice.model.entity.UtilityAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author jolly
 */
public interface UtilityAccountRepository extends JpaRepository<UtilityAccountEntity, Long> {
    @Query("select u from UtilityAccountEntity u where u.providerName = :provider")
    Optional<UtilityAccountEntity> findByProviderName(@Param("provider") String provider);
}
