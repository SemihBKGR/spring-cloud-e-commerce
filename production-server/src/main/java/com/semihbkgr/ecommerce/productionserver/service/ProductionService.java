package com.semihbkgr.ecommerce.productionserver.service;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import reactor.core.publisher.Mono;

public interface ProductionService {

    Mono<Production> save(Production production);

    Mono<Production> update(String id,Production production);

    Mono<Production> findById(String id);

    Mono<Void> deleteById(String id);

}
