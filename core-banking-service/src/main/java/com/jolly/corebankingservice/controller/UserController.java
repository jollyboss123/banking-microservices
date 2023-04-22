package com.jolly.corebankingservice.controller;

import com.jolly.corebankingservice.model.dto.UserDTO;
import com.jolly.corebankingservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jolly
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{identification}")
    public ResponseEntity<UserDTO> readUser(@PathVariable("identification") String identification) {
        return ResponseEntity.ok(userService.readUser(identification));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> readUser(Pageable pageable) {
        return ResponseEntity.ok(userService.readUsers(pageable));
    }
}
