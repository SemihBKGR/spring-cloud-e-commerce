package com.semihbkgr.ecommerce.imageserver.repository;

import com.semihbkgr.ecommerce.modelcommon.image.ProfileImage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProfileImageRepository extends ReactiveMongoRepository<ProfileImage, String> {

}
