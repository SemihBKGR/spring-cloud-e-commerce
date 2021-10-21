package com.semihbkgr.ecommerce.ecommerce.userserver.controller;

import com.semihbkgr.ecommerce.ecommerce.userserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public List<UserRepresentation> searchUser(@PathVariable("username") String username) {
        return userService.searchUserByUsername(username);
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }

}
