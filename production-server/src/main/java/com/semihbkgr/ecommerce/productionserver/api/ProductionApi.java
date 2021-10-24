package com.semihbkgr.ecommerce.productionserver.api;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import com.semihbkgr.ecommerce.modelcommon.production.ProductionInfo;
import com.semihbkgr.ecommerce.productionserver.component.KafkaProductionLogSender;
import com.semihbkgr.ecommerce.productionserver.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProductionApi {

    public static final int DEFAULT_PRODUCTION_PAGE_SIZE = 5;

    private final ProductionService productionService;
    private final KafkaProductionLogSender kafkaProductionLogProducer;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<Production> save(@RequestBody Production production) {
        return productionService.save(production)
                .flatMap(productionFromDb -> kafkaProductionLogProducer
                        .log(KafkaProductionLogSender.ProductionActionType.CREATE, production)
                        .thenReturn(productionFromDb));
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{production-id}")
    public Mono<Production> update(@PathVariable("production-id") String productionId,
                                   @RequestBody Production production) {
        return productionService.update(productionId, production)
                .flatMap(productionFromDb -> kafkaProductionLogProducer
                        .log(KafkaProductionLogSender.ProductionActionType.UPDATE, production)
                        .thenReturn(productionFromDb));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{production-id}")
    public Mono<Production> findById(@PathVariable("production-id") String productionId) {
        return productionService.findById(productionId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Flux<ProductionInfo> findAll(@RequestParam(value = "p", required = false, defaultValue = "0") int page) {
        return productionService.findAllInfos(PageRequest.of(page, DEFAULT_PRODUCTION_PAGE_SIZE).withSort(Sort.by("name").ascending()));
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{production-id}")
    public Mono<Void> deleteById(@PathVariable("production-id") String productionId) {
        return productionService.deleteById(productionId);
    }

}
