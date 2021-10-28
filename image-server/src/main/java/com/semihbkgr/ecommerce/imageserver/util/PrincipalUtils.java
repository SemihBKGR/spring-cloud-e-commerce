package com.semihbkgr.ecommerce.imageserver.util;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

import javax.servlet.http.HttpServletRequest;

public class PrincipalUtils {

    public static String getUsername(HttpServletRequest request){
        var token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        var principal=(KeycloakPrincipal)token.getPrincipal();
        var session = principal.getKeycloakSecurityContext();
        var accessToken = session.getToken();
        return accessToken.getPreferredUsername();
    }

}
