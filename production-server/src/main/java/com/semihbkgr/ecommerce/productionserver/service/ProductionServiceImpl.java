package com.semihbkgr.ecommerce.productionserver.service;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import com.semihbkgr.ecommerce.productionserver.component.IdGenerator;
import com.semihbkgr.ecommerce.productionserver.repository.ProductionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;
    private final IdGenerator idGenerator;

    @Override
    public Mono<Production> save(@NonNull Production production) {
        return productionRepository.insert(production.withId(idGenerator.generate()));
    }

    @Override
    public Mono<Production> update(@NonNull String id, @NonNull Production production) {
        return productionRepository.insert(production.withId(id));
    }

    @Override
    public Mono<Production> findById(@NonNull String id) {
        return productionRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(@NonNull String id) {
        return productionRepository.deleteById(id);
    }

}
