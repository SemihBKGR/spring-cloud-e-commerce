package com.semihbkgr.ecommerce.productionserver.api;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import com.semihbkgr.ecommerce.modelcommon.production.ProductionInfo;
import com.semihbkgr.ecommerce.productionserver.component.KafkaProductionLogSender;
import com.semihbkgr.ecommerce.productionserver.service.ProductionService;
import com.semihbkgr.ecommerce.productionserver.util.PrincipalUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
public class ProductionApi {

    public static final int DEFAULT_PRODUCTION_PAGE_SIZE = 5;

    private final ProductionService productionService;
    private final KafkaProductionLogSender kafkaProductionLogProducer;

    @GetMapping("/{production-id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Production> findById(@PathVariable("production-id") String productionId) {
        return productionService.findById(productionId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductionInfo> findAllInfos(@RequestParam(value = "p", required = false, defaultValue = "0") int page) {
        page=Math.max(page,0);
        return productionService.findAllInfos(
                PageRequest.of(page, DEFAULT_PRODUCTION_PAGE_SIZE)
                        .withSort(Sort.by("name").ascending()));
    }

    @GetMapping("/owner/{owner}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductionInfo> findAllInfosByOwner(@PathVariable("owner") String owner,
                                                    @RequestParam(value = "p",required = false,defaultValue = "0") int page){
        page=Math.max(page,0);
        return productionService.findAllInfosByOwner(owner,
                PageRequest.of(0,DEFAULT_PRODUCTION_PAGE_SIZE)
                        .withPage(page)
                        .withSort(Sort.by("createdAt").descending()));
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductionInfo> searchByName(@RequestParam("s") String search,
                                             @RequestParam(value = "p", required = false, defaultValue = "0") int page) {
        return productionService.searchByName(search,
                PageRequest.of(page, DEFAULT_PRODUCTION_PAGE_SIZE)
                        .withSort(Sort.by("name").ascending()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Production> save(@RequestBody Production production, WebRequest request) {
        var username = PrincipalUtils.getUsername(request);
        return productionService.save(username, production)
                .flatMap(productionFromDb ->
                        kafkaProductionLogProducer
                                .log(KafkaProductionLogSender.ProductionActionType.CREATE, productionFromDb, username)
                                .thenReturn(productionFromDb));
    }

    @PutMapping("/{production-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Production> update(@PathVariable("production-id") String productionId,
                                   @RequestBody Production production, WebRequest request) {
        var username = PrincipalUtils.getUsername(request);
        return productionService.update(productionId, username, production)
                .flatMap(productionFromDb ->
                        kafkaProductionLogProducer
                                .log(KafkaProductionLogSender.ProductionActionType.UPDATE, productionFromDb, username)
                                .thenReturn(productionFromDb));
    }

    @DeleteMapping("/{production-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Production> deleteById(@PathVariable("production-id") String productionId, WebRequest request) {
        var username = PrincipalUtils.getUsername(request);
        return productionService.deleteById(productionId, username)
                .flatMap(productionFromDb ->
                        kafkaProductionLogProducer
                                .log(KafkaProductionLogSender.ProductionActionType.DELETE, productionFromDb, username)
                                .thenReturn(productionFromDb));
    }

}
