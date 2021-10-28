package com.semihbkgr.ecommerce.imageserver.service;

import com.semihbkgr.ecommerce.modelcommon.image.ProductionImage;

import java.util.List;

public interface ProductionImageService {

    ProductionImage save(ProductionImage image);

    ProductionImage update(String id, ProductionImage image);

    ProductionImage findById(String id);

    List<ProductionImage> findAllByProductionId(String productionId);

    ProductionImage deleteById(String id);

}
