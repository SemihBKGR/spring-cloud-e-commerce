package com.semihbkgr.ecommerce.productionserver.util;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.web.context.request.WebRequest;

public class PrincipalUtils {

    public static String getUsername(WebRequest request) {
        var token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        var principal = (KeycloakPrincipal) token.getPrincipal();
        var session = principal.getKeycloakSecurityContext();
        var accessToken = session.getToken();
        return accessToken.getPreferredUsername();
    }

}
