package com.semihbkgr.ecommerce.imageserver.repository;

import com.semihbkgr.ecommerce.modelcommon.image.ProfileImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileImageRepository extends MongoRepository<ProfileImage, String> {

}
