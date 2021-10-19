package com.semihbkgr.ecommerce.ecommerce.userserver.controller;

import com.semihbkgr.ecommerce.ecommerce.userserver.model.User;
import com.semihbkgr.ecommerce.ecommerce.userserver.model.dto.UserCreateDto;
import com.semihbkgr.ecommerce.ecommerce.userserver.service.UserService;
import com.semihbkgr.ecommerce.ecommerce.userserver.util.ModelUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> getUserById(@PathVariable("id")int id){
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> createUser(@RequestBody UserCreateDto userCreateDto){
        return userService.save(ModelUtils.userOf(userCreateDto));
    }

}
