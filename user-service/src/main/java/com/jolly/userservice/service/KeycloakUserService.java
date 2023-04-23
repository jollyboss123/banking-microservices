package com.jolly.userservice.service;

import com.jolly.userservice.config.KeycloakManager;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * @author jolly
 */
@Service
@RequiredArgsConstructor
public class KeycloakUserService {
    private final KeycloakManager keycloakManager;

    public Integer createUser(UserRepresentation userRepresentation) {
        try (Response response = keycloakManager.getKeycloakInstanceWithRealm()
                .users()
                .create(userRepresentation)) {
            return response.getStatus();
        }
    }

    public void updateUser(UserRepresentation userRepresentation) {
        keycloakManager.getKeycloakInstanceWithRealm()
                .users()
                .get(userRepresentation.getId())
                .update(userRepresentation);
    }

    public List<UserRepresentation> readUserByEmail(String email) {
        return keycloakManager.getKeycloakInstanceWithRealm()
                .users()
                .search(email);
    }

    public UserRepresentation readUser(String authId) {
        Optional<UserResource> userResource = Optional.ofNullable(keycloakManager.getKeycloakInstanceWithRealm()
                .users()
                .get(authId));
        return userResource.map(UserResource::toRepresentation)
                .orElseThrow(() -> new RuntimeException("User not found for given ID"));
    }
}
