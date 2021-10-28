package com.semihbkgr.ecommerce.imageserver.util;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

import javax.servlet.http.HttpServletRequest;

public class PrincipalUtils {

    public static final String AUTHENTICATION_TOKEN_PREFIX = "Bearer ";

    public static String getUsername(HttpServletRequest request) {
        var token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        var principal = (KeycloakPrincipal) token.getPrincipal();
        var session = principal.getKeycloakSecurityContext();
        var accessToken = session.getToken();
        return accessToken.getPreferredUsername();
    }

    public static String getAuthenticationHeader(HttpServletRequest request) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal<?> principal = (KeycloakPrincipal<?>) token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        return concatAuthenticationPrefix(session.getTokenString());
    }

    private static String concatAuthenticationPrefix(String token) {
        return AUTHENTICATION_TOKEN_PREFIX.concat(token);
    }

}
