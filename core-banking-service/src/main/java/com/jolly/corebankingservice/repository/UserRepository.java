package com.jolly.corebankingservice.repository;

import com.jolly.corebankingservice.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author jolly
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select u from UserEntity u where u.identificationNumber = :identificationNumber")
    Optional<UserEntity> findByIdentificationNumber(@Param("identificationNumber") String identificationNumber);
}
