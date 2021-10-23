package com.semihbkgr.ecommerce.productionserver.api;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import com.semihbkgr.ecommerce.productionserver.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProductionApi {

    private final ProductionService productionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<Production> save(@RequestBody Production production) {
        return productionService.save(production);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{production-id}")
    public Mono<Production> update(@PathVariable("production-id") String productionId,
                                   @RequestBody Production production) {
        return productionService.update(productionId, production);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{production-id}")
    public Mono<Production> findById(@PathVariable("production-id") String productionId) {
        return productionService.findById(productionId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{production-id}")
    public Mono<Void> deleteById(@PathVariable("production-id") String productionId) {
        return productionService.deleteById(productionId);
    }

}
