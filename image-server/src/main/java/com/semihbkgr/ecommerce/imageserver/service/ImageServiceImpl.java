package com.semihbkgr.ecommerce.imageserver.service;

import com.semihbkgr.ecommerce.imageserver.repository.ImageRepository;
import com.semihbkgr.ecommerce.modelcommon.image.Image;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Mono<Image> save(@NonNull Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Mono<Image> update(@NonNull String id, @NonNull Image image) {
        return findById(id)
                .flatMap(imageFromDb -> {
                    imageFromDb.setName(image.getName());
                    imageFromDb.setWidth(image.getWidth());
                    imageFromDb.setHeight(image.getHeight());
                    imageFromDb.setSize(image.getSize());
                    return imageRepository.save(imageFromDb);
                });
    }

    @Override
    public Mono<Image> findById(@NonNull String id) {
        return imageRepository.findById(id);
    }

    @Override
    public Mono<Image> deleteById(@NonNull String id) {
        return findById(id)
                .flatMap(imageFromDb ->
                        imageRepository.deleteById(id)
                                .thenReturn(imageFromDb));
    }
}
