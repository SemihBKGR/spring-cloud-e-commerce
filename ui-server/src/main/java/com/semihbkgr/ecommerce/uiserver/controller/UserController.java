package com.semihbkgr.ecommerce.uiserver.controller;

import com.semihbkgr.ecommerce.uiserver.client.UserClient;
import com.semihbkgr.ecommerce.uiserver.model.User;
import com.semihbkgr.ecommerce.uiserver.util.HeaderUtils;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserClient userClient;

    @GetMapping("/get/{username}")
    public User getUserByUsername(@PathVariable("username")String username, HttpServletRequest request){
        return userClient.getByUsername(username, HeaderUtils.getAuthenticationHeader(request));
    }

    @GetMapping("/search/{username}")
    public List<User> searchUserByUsername(@PathVariable("username")String username, HttpServletRequest request){
        return userClient.searchByUsername(username, HeaderUtils.getAuthenticationHeader(request));
    }

}
