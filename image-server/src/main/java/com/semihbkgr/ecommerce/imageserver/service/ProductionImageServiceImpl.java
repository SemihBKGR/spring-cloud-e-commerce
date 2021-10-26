package com.semihbkgr.ecommerce.imageserver.service;

import com.semihbkgr.ecommerce.imageserver.component.IdGenerator;
import com.semihbkgr.ecommerce.imageserver.repository.ProductionImageRepository;
import com.semihbkgr.ecommerce.modelcommon.image.ProductionImage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductionImageServiceImpl implements ProductionImageService {

    private final ProductionImageRepository productionImageRepository;
    private final IdGenerator idGenerator;

    @Override
    public Mono<ProductionImage> save(@NonNull ProductionImage image) {
        return productionImageRepository.save(image.withId(idGenerator.generate()));
    }

    @Override
    public Mono<ProductionImage> update(@NonNull String id, @NonNull ProductionImage image) {
        return productionImageRepository.findById(id)
                .flatMap(imageFromDb -> {
                    imageFromDb.setDisplayOrder(image.getDisplayOrder());
                    imageFromDb.setSize(image.getSize());
                    imageFromDb.setWidth(image.getWidth());
                    imageFromDb.setHeight(image.getHeight());
                    return productionImageRepository.save(imageFromDb);
                });
    }

    @Override
    public Mono<ProductionImage> findById(@NonNull String id) {
        return productionImageRepository.findById(id);
    }

    @Override
    public Flux<ProductionImage> findAllByProductionId(@NonNull String productionId) {
        return productionImageRepository.findAllByProductionIdOrderByDisplayOrderAsc(productionId);
    }

    @Override
    public Mono<ProductionImage> deleteById(String id) {
        return productionImageRepository.findById(id)
                .flatMap(imageFromDb ->
                        productionImageRepository.deleteById(id)
                                .thenReturn(imageFromDb));
    }

}
