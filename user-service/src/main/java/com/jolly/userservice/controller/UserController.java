package com.jolly.userservice.controller;

import com.jolly.userservice.model.dto.UserDTO;
import com.jolly.userservice.model.dto.request.UserUpdateRequest;
import com.jolly.userservice.service.KeycloakUserService;
import com.jolly.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jolly
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/bank-user")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO request) {
        log.info("Creating user with {}", request.toString());

        return ResponseEntity.ok(userService.createUser(request));
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long userId,
                                              @RequestBody UserUpdateRequest request) {
        log.info("Updating user of id: {} with {}", userId, request.toString());

        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> readUsers(Pageable pageable) {
        log.info("Reading all users from API");

        return ResponseEntity.ok(userService.readUsers(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> readUser(@PathVariable("id") Long id) {
        log.info("Reading user with id: {}", id);

        return ResponseEntity.ok(userService.readUser(id));
    }
}
