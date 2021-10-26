package com.semihbkgr.ecommerce.imageserver.service;

import com.semihbkgr.ecommerce.modelcommon.image.ProductionImage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductionImageService {

    Mono<ProductionImage> save(ProductionImage image);

    Mono<ProductionImage> update(String id, ProductionImage image);

    Mono<ProductionImage> findById(String id);

    Flux<ProductionImage> findAllByProductionId(String productionId);

    Mono<ProductionImage> deleteById(String id);

}
