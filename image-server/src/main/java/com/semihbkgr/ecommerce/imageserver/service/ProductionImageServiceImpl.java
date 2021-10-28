package com.semihbkgr.ecommerce.imageserver.service;

import com.semihbkgr.ecommerce.imageserver.component.IdGenerator;
import com.semihbkgr.ecommerce.imageserver.repository.ProductionImageRepository;
import com.semihbkgr.ecommerce.modelcommon.image.ProductionImage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductionImageServiceImpl implements ProductionImageService {

    private final ProductionImageRepository productionImageRepository;
    private final IdGenerator idGenerator;

    @Override
    public ProductionImage save(@NonNull ProductionImage image) {
        return productionImageRepository.save(image.withId(idGenerator.generate()));
    }

    @Override
    public ProductionImage update(@NonNull String id, @NonNull ProductionImage image) {
        var imageFromDbOpt = productionImageRepository.findById(id);
        if (imageFromDbOpt.isEmpty())
            return null;
        var imageFromDb = imageFromDbOpt.get();
        imageFromDb.setDisplayOrder(image.getDisplayOrder());
        imageFromDb.setSize(image.getSize());
        return productionImageRepository.save(imageFromDb);
    }

    @Override
    public ProductionImage findById(@NonNull String id) {
        var imageFromDbOpt = productionImageRepository.findById(id);
        if (imageFromDbOpt.isEmpty())
            return null;
        return imageFromDbOpt.get();
    }

    @Override
    public List<ProductionImage> findAllByProductionId(@NonNull String productionId) {
        return productionImageRepository.findAllByProductionIdOrderByDisplayOrderAsc(productionId);
    }

    @Override
    public ProductionImage deleteById(String id) {
        var imageFromDbOpt = productionImageRepository.findById(id);
        if (imageFromDbOpt.isEmpty())
            return null;
        productionImageRepository.deleteById(id);
        return imageFromDbOpt.get();
    }

}
