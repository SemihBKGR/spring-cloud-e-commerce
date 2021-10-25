package com.semihbkgr.ecommerce.productionserver.repository;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import com.semihbkgr.ecommerce.modelcommon.production.ProductionInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductionRepository extends ReactiveMongoRepository<Production, String> {

    Flux<ProductionInfo> findAllBy(Pageable pageable);

    Flux<ProductionInfo> findAllByNameContaining(String name,Pageable pageable);

}
