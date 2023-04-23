package com.jolly.userservice.model.mapper;

import com.jolly.userservice.model.dto.UserDTO;
import com.jolly.userservice.model.entity.UserEntity;
import org.springframework.beans.BeanUtils;

/**
 * @author jolly
 */
public class UserMapper extends BaseMapper<UserEntity, UserDTO>{
    @Override
    public UserEntity convertToEntity(UserDTO dto, Object... args) {
        UserEntity userEntity = new UserEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, userEntity);
        }
        return userEntity;
    }

    @Override
    public UserDTO convertToDto(UserEntity entity, Object... args) {
        UserDTO user = new UserDTO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, user);
        }
        return user;
    }
}
