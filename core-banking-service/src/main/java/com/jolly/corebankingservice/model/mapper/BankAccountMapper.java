package com.jolly.corebankingservice.model.mapper;

import com.jolly.corebankingservice.model.dto.BankAccountDTO;
import com.jolly.corebankingservice.model.entity.BankAccountEntity;
import org.springframework.beans.BeanUtils;

/**
 * @author jolly
 */
public class BankAccountMapper extends BaseMapper<BankAccountEntity, BankAccountDTO> {
    @Override
    public BankAccountEntity convertToEntity(BankAccountDTO dto, Object... args) {
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, bankAccountEntity, "user");
        }
        return bankAccountEntity;
    }

    @Override
    public BankAccountDTO convertToDto(BankAccountEntity entity, Object... args) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, bankAccountDTO, "user");
        }
        return bankAccountDTO;
    }
}
