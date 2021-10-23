package com.semihbkgr.ecommerce.productionserver.service;

import com.semihbkgr.ecommerce.productionserver.document.ProductionDocument;

public interface ProductionService {

    ProductionDocument save(ProductionDocument production);

    ProductionDocument findById(String id);



}
