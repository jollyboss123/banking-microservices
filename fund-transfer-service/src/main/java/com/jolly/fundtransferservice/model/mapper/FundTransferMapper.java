package com.jolly.fundtransferservice.model.mapper;

import com.jolly.fundtransferservice.model.dto.FundTransfer;
import com.jolly.fundtransferservice.model.entity.FundTransferEntity;
import org.springframework.beans.BeanUtils;

/**
 * @author jolly
 */
public class FundTransferMapper extends BaseMapper<FundTransferEntity, FundTransfer> {
    @Override
    public FundTransferEntity convertToEntity(FundTransfer dto, Object... args) {
        FundTransferEntity entity = new FundTransferEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }

    @Override
    public FundTransfer convertToDto(FundTransferEntity entity, Object... args) {
        FundTransfer dto = new FundTransfer();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
}
