package com.semihbkgr.ecommerce.uiserver.util;

import lombok.NonNull;
import org.apache.http.auth.AUTH;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;

public class HeaderUtils {

    public static final String AUTHENTICATION_TOKEN_PREFIX="Bearer ";

    @Nullable
    public static String getAuthenticationHeader(@NonNull HttpServletRequest request){
        return getAuthenticationHeader((KeycloakAuthenticationToken) request.getUserPrincipal());
    }

    @Nullable
    public static String getAuthenticationHeader(@NonNull KeycloakAuthenticationToken token){
        if(!token.isAuthenticated())
            return null;
        KeycloakPrincipal<?> principal=(KeycloakPrincipal<?>)token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        return concatAuthenticationPrefix(session.getTokenString());
    }

    private static String concatAuthenticationPrefix(String token){
        return AUTHENTICATION_TOKEN_PREFIX.concat(token);
    }

}
