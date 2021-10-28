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
    public Mono<Production> save(@NonNull String owner, @NonNull Production production) {
        var productionToDb = production.withId(idGenerator.generate());
        productionToDb.setOwner(owner);
        return productionRepository.save(productionToDb);
    }

    @Override
    public Mono<Production> update(@NonNull String id, @NonNull String owner, @NonNull Production production) {
        return productionRepository.findById(id)
                .filter(productionFromDb -> productionFromDb.getOwner().equals(owner))
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
    public Flux<ProductionInfo> findAllInfosByOwner(@NonNull String owner,@NonNull Pageable pageable) {
        return productionRepository.findAllByOwner(owner,pageable);
    }

    @Override
    public Flux<ProductionInfo> searchByName(@NonNull String name, @NonNull Pageable pageable) {
        return productionRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Mono<Production> deleteById(@NonNull String id, @NonNull String owner) {
        return productionRepository.findById(id)
                .filter(productionFromDb -> productionFromDb.getOwner().equals(owner))
                .flatMap(productionFromDb ->
                        productionRepository.deleteById(id)
                                .thenReturn(productionFromDb));
    }

}
