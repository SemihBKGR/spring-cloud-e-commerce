package com.semihbkgr.ecommerce.imageserver.repository;

import com.semihbkgr.ecommerce.modelcommon.image.ProductionImage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductionImageRepository extends MongoRepository<ProductionImage, String> {

    List<ProductionImage> findAllByProductionIdOrderByDisplayOrderAsc(String productionId);

}
