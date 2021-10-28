package com.semihbkgr.ecommerce.imageserver.client;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PRODUCTION")
public interface ProductionClient {

    @Nullable
    @GetMapping("/{production-id}")
    Production findById(@PathVariable("production-id") String productionId);

}