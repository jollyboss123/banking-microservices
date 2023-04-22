package com.jolly.corebankingservice.model.mapper;

import com.jolly.corebankingservice.model.dto.UserDTO;
import com.jolly.corebankingservice.model.entity.UserEntity;
import org.springframework.beans.BeanUtils;

/**
 * @author jolly
 */
public class UserMapper extends BaseMapper<UserEntity, UserDTO> {
    private final BankAccountMapper bankAccountMapper = new BankAccountMapper();
    @Override
    public UserEntity convertToEntity(UserDTO dto, Object... args) {
        UserEntity userEntity = new UserEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, userEntity, "accounts");
            userEntity.setAccounts(bankAccountMapper.convertToEntityList(dto.getBankAccounts()));
        }
        return userEntity;
    }

    @Override
    public UserDTO convertToDto(UserEntity entity, Object... args) {
        UserDTO userDTO = new UserDTO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, userDTO, "accounts");
            userDTO.setBankAccounts(bankAccountMapper.convertToDtoList(entity.getAccounts()));
        }
        return userDTO;
    }
}
