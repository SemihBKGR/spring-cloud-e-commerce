package com.semihbkgr.ecommerce.imageserver.repository;

import com.semihbkgr.ecommerce.modelcommon.image.ProductionImage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductionImageRepository extends ReactiveMongoRepository<ProductionImage, String> {

    Flux<ProductionImage> findAllByProductionIdOrderByDisplayOrderAsc(String productionId);

}
