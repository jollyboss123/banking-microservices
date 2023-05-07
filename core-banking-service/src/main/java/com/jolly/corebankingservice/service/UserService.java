package com.jolly.corebankingservice.service;

import com.jolly.corebankingservice.model.dto.UserDTO;
import com.jolly.corebankingservice.model.entity.UserEntity;
import com.jolly.corebankingservice.model.mapper.UserMapper;
import com.jolly.corebankingservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jolly
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper = new UserMapper();
    private final UserRepository userRepository;

    public UserDTO readUser(String identificationNumber) {
        UserEntity userEntity = userRepository
                .findByIdentificationNumber(identificationNumber)
                .orElse(null);
        return userMapper.convertToDto(userEntity);
    }

    public List<UserDTO> readUsers(Pageable pageable) {
        return userMapper.convertToDtoList(userRepository.findAll(pageable).getContent());
    }
}
