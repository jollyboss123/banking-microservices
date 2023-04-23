package com.jolly.userservice.model.dto.request;

import com.jolly.userservice.model.Status;
import lombok.Data;

/**
 * @author jolly
 */
@Data
public class UserUpdateRequest {
    private Status status;
}
