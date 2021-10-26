package com.semihbkgr.ecommerce.imageserver.service;

import com.semihbkgr.ecommerce.imageserver.component.IdGenerator;
import com.semihbkgr.ecommerce.imageserver.repository.ProfileImageRepository;
import com.semihbkgr.ecommerce.modelcommon.image.ProfileImage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProfileImageServiceImpl implements ProfileImageService {

    private final ProfileImageRepository imageRepository;
    private final IdGenerator idGenerator;

    @Override
    public Mono<ProfileImage> save(@NonNull ProfileImage image) {
        return imageRepository.save(image.withId(idGenerator.generate()));
    }

    @Override
    public Mono<ProfileImage> update(@NonNull String id, @NonNull ProfileImage image) {
        return imageRepository.findById(id)
                .flatMap(imageFromDb -> {
                    imageFromDb.setExtension(image.getExtension());
                    imageFromDb.setWidth(image.getWidth());
                    imageFromDb.setHeight(image.getHeight());
                    imageFromDb.setSize(image.getSize());
                    return imageRepository.save(imageFromDb);
                });
    }

    @Override
    public Mono<ProfileImage> findById(@NonNull String id) {
        return imageRepository.findById(id);
    }

    @Override
    public Mono<ProfileImage> deleteById(@NonNull String id) {
        return imageRepository.findById(id)
                .flatMap(imageFromDb ->
                        imageRepository.deleteById(id)
                                .thenReturn(imageFromDb));
    }

}
