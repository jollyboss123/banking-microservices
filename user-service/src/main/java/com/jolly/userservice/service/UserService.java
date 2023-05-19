package com.jolly.userservice.service;

import com.jolly.userservice.exception.GlobalErrorCode;
import com.jolly.userservice.exception.InvalidBankingUserException;
import com.jolly.userservice.exception.InvalidEmailException;
import com.jolly.userservice.exception.UserAlreadyRegisteredException;
import com.jolly.userservice.model.Status;
import com.jolly.userservice.model.dto.UserDTO;
import com.jolly.userservice.model.dto.request.UserUpdateRequest;
import com.jolly.userservice.model.entity.UserEntity;
import com.jolly.userservice.model.mapper.UserMapper;
import com.jolly.userservice.model.repository.UserRepository;
import com.jolly.userservice.model.rest.UserResponse;
import com.jolly.userservice.service.rest.BankingCoreRestClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author jolly
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final KeycloakUserService keycloakUserService;
    private final UserRepository userRepository;
    private final BankingCoreRestClient bankingCoreRestClient;

    private final UserMapper userMapper = new UserMapper();

    public UserDTO createUser(UserDTO userDTO) {
        List<UserRepresentation> userRepresentations = keycloakUserService.readUserByEmail(userDTO.getEmail());

        if (userRepresentations.size() > 0) {
            throw new UserAlreadyRegisteredException("This email has been registered as a user. Please check and retry.", GlobalErrorCode.ERROR_EMAIL_REGISTERED);
        }

        UserResponse userResponse = bankingCoreRestClient.readUser(userDTO.getIdentification());

        if (userResponse != null) {

            if (userResponse.getEmail() == null || !userResponse.getEmail().equals(userDTO.getEmail())) {
                throw new InvalidEmailException("Incorrect email. Please check and retry.", GlobalErrorCode.ERROR_INVALID_EMAIL);
            }

            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setEmail(userDTO.getEmail());
            userRepresentation.setEmailVerified(false);
            userRepresentation.setEnabled(false);
            userRepresentation.setUsername(userDTO.getEmail());

            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setValue(userDTO.getPassword());
            credentialRepresentation.setTemporary(false);
            userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

            Integer userCreationResponse = keycloakUserService.createUser(userRepresentation);

            if (userCreationResponse == 201) {
                log.info("User created under given username: {}", userRepresentation.getUsername());

                List<UserRepresentation> userRepresentationList = keycloakUserService.readUserByEmail(userRepresentation.getEmail());
                userDTO.setAuthId(userRepresentationList.get(0).getId());
                userDTO.setStatus(Status.PENDING);
                userDTO.setIdentification(UUID.randomUUID().toString());

                UserEntity userEntity = userRepository.save(userMapper.convertToEntity(userDTO));
                return userMapper.convertToDto(userEntity);
            }
        }

        throw new InvalidBankingUserException("We couldn't find user under given identification. Please check and retry", GlobalErrorCode.ERROR_USER_NOT_FOUND_UNDER_NIC);
    }

    public List<UserDTO> readUsers(Pageable pageable) {
        Page<UserEntity> allUsersInDb = userRepository.findAll(pageable);

        List<UserDTO> userDTOs = userMapper.convertToDtoList(allUsersInDb.getContent());

        // using peek instead of map because updating UserDTO in place
        // no transformation to new objects
        return userDTOs.stream()
                .peek(user -> {
                    UserRepresentation userRepresentation = keycloakUserService.readUser(user.getAuthId());
                    user.setId(user.getId());
                    user.setEmail(userRepresentation.getEmail());
                    user.setIdentification(user.getIdentification());
                })
                .collect(Collectors.toList());
    }

    public UserDTO readUser(Long userId) {
        return userMapper.convertToDto(userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new));
    }

    public UserDTO updateUser(Long id, UserUpdateRequest request) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (request.getStatus() == Status.APPROVED) {
            UserRepresentation userRepresentation = keycloakUserService.readUser(userEntity.getAuthId());
            userRepresentation.setEnabled(true);
            userRepresentation.setEmailVerified(true);
            keycloakUserService.updateUser(userRepresentation);
        }

        userEntity.setStatus(request.getStatus());
        return userMapper.convertToDto(userRepository.save(userEntity));
    }
}
