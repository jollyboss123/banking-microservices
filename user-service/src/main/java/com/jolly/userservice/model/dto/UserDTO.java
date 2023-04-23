package com.jolly.userservice.model.dto;

import com.jolly.userservice.model.Status;
import lombok.Data;

/**
 * @author jolly
 */
@Data
public class UserDTO {
    private Long id;
    private String email;
    private String identification;
    private String password;
    private String authId;
    private Status status;
}
