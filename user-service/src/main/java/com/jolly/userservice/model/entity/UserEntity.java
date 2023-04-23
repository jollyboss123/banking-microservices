package com.jolly.userservice.model.entity;

import com.jolly.userservice.model.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jolly
 */
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String authId;
    private String identification;
    @Enumerated(EnumType.STRING)
    private Status status;

}
