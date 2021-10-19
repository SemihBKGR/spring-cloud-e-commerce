package com.semihbkgr.ecommerce.ecommerce.userserver.service;

import com.semihbkgr.ecommerce.ecommerce.userserver.model.User;
import com.semihbkgr.ecommerce.ecommerce.userserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<User> save(User user) {
        return userRepository.save(user.withId(0));
    }

}
