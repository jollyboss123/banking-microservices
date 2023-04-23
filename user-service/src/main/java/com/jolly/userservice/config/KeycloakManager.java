package com.jolly.userservice.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.stereotype.Component;

/**
 * @author jolly
 */

/**
 * RealmResource to communicate with keycloakInstance
 */
@Component
@RequiredArgsConstructor
public class KeycloakManager {
    private final KeycloakProperties keycloakProperties;

    public RealmResource getKeycloakInstanceWithRealm() {
        return keycloakProperties.getInstance().realm(keycloakProperties.getRealm());
    }
}
