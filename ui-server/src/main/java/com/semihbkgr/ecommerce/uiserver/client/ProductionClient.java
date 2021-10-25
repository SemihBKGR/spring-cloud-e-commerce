package com.semihbkgr.ecommerce.uiserver.client;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import com.semihbkgr.ecommerce.modelcommon.production.ProductionInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@FeignClient("PRODUCTION")
public interface ProductionClient {

    @NonNull
    @PostMapping
    Production save(@RequestBody Production production,
                    @RequestHeader("Authorization") String authorization);

    @Nullable
    @PutMapping("/{production-id}")
    Production update(@PathVariable("production-id") String productionId,
                      @RequestBody Production production,
                      @RequestHeader("Authorization") String authorization);

    @Nullable
    @GetMapping("/{production-id}")
    Production findById(@PathVariable("production-id") String productionId,
                        @RequestHeader("Authorization") String authorization);

    @NonNull
    @GetMapping
    ProductionInfo findAllInfos(@RequestHeader("Authorization") String authorization);

    @Nullable
    @DeleteMapping("/{production-id}")
    Mono<Production> deleteById(@PathVariable("production-id") String productionId,
                                @RequestHeader("Authorization") String authorization);

}
