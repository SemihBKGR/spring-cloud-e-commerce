package com.semihbkgr.ecommerce.productionserver.service;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import com.semihbkgr.ecommerce.modelcommon.production.ProductionInfo;
import com.semihbkgr.ecommerce.productionserver.component.IdGenerator;
import com.semihbkgr.ecommerce.productionserver.repository.ProductionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;
    private final IdGenerator idGenerator;

    @Override
    public Mono<Production> save(@NonNull Production production) {
        return productionRepository.save(production.withId(idGenerator.generate()));
    }

    @Override
    public Mono<Production> update(@NonNull String id, @NonNull Production production) {
        return findById(id)
                .flatMap(productionFromDb -> {
                    productionFromDb.setName(production.getName());
                    productionFromDb.setPrice(production.getPrice());
                    productionFromDb.setStock(production.getStock());
                    productionFromDb.setDescription(production.getDescription());
                    return productionRepository.save(productionFromDb);
                });
    }

    @Override
    public Mono<Production> findById(@NonNull String id) {
        return productionRepository.findById(id);
    }

    @Override
    public Flux<ProductionInfo> findAllInfos(@NonNull Pageable pageable) {
        return productionRepository.findAllBy(pageable);
    }

    @Override
    public Mono<Production> deleteById(@NonNull String id) {
        return findById(id)
                .flatMap(productionFromDb ->
                        productionRepository.deleteById(id)
                                .thenReturn(productionFromDb));
    }

}
