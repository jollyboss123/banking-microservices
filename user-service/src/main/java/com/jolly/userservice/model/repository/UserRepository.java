package com.jolly.userservice.model.repository;

import com.jolly.userservice.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jolly
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
