package com.semihbkgr.ecommerce.productionserver.repository;

import com.semihbkgr.ecommerce.productionserver.document.ProductionDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductionRepository extends ReactiveMongoRepository<ProductionDocument,String> {
}
