package com.semihbkgr.ecommerce.productionserver.test.util;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import com.semihbkgr.ecommerce.modelcommon.production.ProductionInfo;

public class ProductionUtils {

    public static Production instance() {
        return instance(null);
    }

    public static Production instance(String id) {
        return Production.builder()
                .id(id)
                .name("test-production-name")
                .owner("test-production-owner")
                .price(14f)
                .stock(170)
                .description("test-production-description")
                .build();
    }

    public static ProductionInfo infoInstance() {
        return infoInstance(null);
    }

    public static ProductionInfo infoInstance(String id) {
        return ProductionInfo.builder()
                .id(id)
                .name("test-production-name")
                .owner("test-production-owner")
                .price(14f)
                .stock(170)
                .build();
    }

}
