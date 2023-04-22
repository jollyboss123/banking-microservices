package com.jolly.corebankingservice.model.mapper;

import com.jolly.corebankingservice.model.dto.UtilityAccountDTO;
import com.jolly.corebankingservice.model.entity.UtilityAccountEntity;
import org.springframework.beans.BeanUtils;

/**
 * @author jolly
 */
public class UtilityAccountMapper extends BaseMapper<UtilityAccountEntity, UtilityAccountDTO> {
    @Override
    public UtilityAccountEntity convertToEntity(UtilityAccountDTO dto, Object... args) {
        UtilityAccountEntity utilityAccountEntity = new UtilityAccountEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, utilityAccountEntity);
        }
        return utilityAccountEntity;
    }

    @Override
    public UtilityAccountDTO convertToDto(UtilityAccountEntity entity, Object... args) {
        UtilityAccountDTO utilityAccountDTO = new UtilityAccountDTO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, utilityAccountDTO);
        }
        return utilityAccountDTO;
    }
}
