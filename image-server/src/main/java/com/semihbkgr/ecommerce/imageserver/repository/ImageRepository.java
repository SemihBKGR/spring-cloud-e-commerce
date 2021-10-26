package com.semihbkgr.ecommerce.imageserver.repository;

import com.semihbkgr.ecommerce.modelcommon.image.Image;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ImageRepository extends ReactiveMongoRepository<Image,String> {

}
