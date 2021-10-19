package com.semihbkgr.ecommerce.ecommerce.userserver.service;

import com.semihbkgr.ecommerce.ecommerce.userserver.model.User;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> findById(int id);

    Mono<User> save(User user);

}
