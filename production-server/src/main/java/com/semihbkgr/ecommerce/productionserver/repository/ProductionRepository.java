package com.semihbkgr.ecommerce.productionserver.repository;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductionRepository extends ReactiveMongoRepository<Production, String> {
}
