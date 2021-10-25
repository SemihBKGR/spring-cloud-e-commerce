package com.semihbkgr.ecommerce.productionserver.service;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import com.semihbkgr.ecommerce.modelcommon.production.ProductionInfo;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductionService {

    Mono<Production> save(Production production);

    Mono<Production> update(String id,Production production);

    Mono<Production> findById(String id);

    Flux<ProductionInfo> findAllInfos(Pageable pageable);

    Flux<ProductionInfo> searchByName(String name,Pageable pageable);

    Mono<Production> deleteById(String id);

}
