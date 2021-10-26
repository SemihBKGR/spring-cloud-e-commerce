package com.semihbkgr.ecommerce.imageserver.service;

import com.semihbkgr.ecommerce.modelcommon.image.ProfileImage;
import reactor.core.publisher.Mono;

public interface ProfileImageService {

    Mono<ProfileImage> save(ProfileImage image);

    Mono<ProfileImage> update(String id, ProfileImage image);

    Mono<ProfileImage> findById(String id);

    Mono<ProfileImage> deleteById(String id);

}
