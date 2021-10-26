package com.semihbkgr.ecommerce.imageserver.service;

import com.semihbkgr.ecommerce.modelcommon.image.Image;
import reactor.core.publisher.Mono;

public interface ImageService {

    Mono<Image> save(Image image);

    Mono<Image> update(String id,Image image);

    Mono<Image> findById(String id);

    Mono<Image> deleteById(String id);

}
