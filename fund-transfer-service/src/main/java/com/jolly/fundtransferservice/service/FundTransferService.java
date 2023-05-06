package com.jolly.fundtransferservice.service;

import com.jolly.fundtransferservice.model.TransactionStatus;
import com.jolly.fundtransferservice.model.dto.FundTransfer;
import com.jolly.fundtransferservice.model.dto.FundTransferRequest;
import com.jolly.fundtransferservice.model.dto.FundTransferResponse;
import com.jolly.fundtransferservice.model.entity.FundTransferEntity;
import com.jolly.fundtransferservice.model.mapper.FundTransferMapper;
import com.jolly.fundtransferservice.model.repository.FundTransferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author jolly
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class FundTransferService {
    private final FundTransferRepository repository;
    private final FundTransferMapper mapper = new FundTransferMapper();

    public FundTransferResponse fundTransfer(FundTransferRequest request) {
        log.info("Sending fund transfer request: {}", request.toString());

        FundTransferEntity entity = new FundTransferEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setStatus(TransactionStatus.PENDING);
        FundTransferEntity optFundTransfer = repository.save(entity);

        FundTransferResponse response = new FundTransferResponse();
        response.setTransactionId(UUID.randomUUID().toString());
        response.setMessage("Success");
        return response;
    }

    public List<FundTransfer> readAllFundTransfers(Pageable pageable) {
        return mapper.convertToDtoList(
                repository.findAll(pageable).getContent()
        );
    }
}
