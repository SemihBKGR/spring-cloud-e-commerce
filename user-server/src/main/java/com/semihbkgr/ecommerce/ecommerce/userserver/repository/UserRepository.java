package com.semihbkgr.ecommerce.ecommerce.userserver.repository;

import com.semihbkgr.ecommerce.ecommerce.userserver.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User,Integer> {

}
