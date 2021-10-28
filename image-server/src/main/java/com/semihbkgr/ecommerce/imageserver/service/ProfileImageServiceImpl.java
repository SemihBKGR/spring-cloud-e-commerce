package com.semihbkgr.ecommerce.imageserver.service;

import com.semihbkgr.ecommerce.imageserver.component.IdGenerator;
import com.semihbkgr.ecommerce.imageserver.repository.ProfileImageRepository;
import com.semihbkgr.ecommerce.modelcommon.image.ProfileImage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileImageServiceImpl implements ProfileImageService {

    private final ProfileImageRepository imageRepository;

    @Override
    public ProfileImage save(@NonNull ProfileImage image) {
        if (image.getId() == null || image.getId().isBlank())
            return null;
        return imageRepository.save(image);
    }

    @Override
    public ProfileImage findById(@NonNull String id) {
        var imageFromDbOpt = imageRepository.findById(id);
        if (imageFromDbOpt.isEmpty())
            return null;
        return imageFromDbOpt.get();
    }

    @Override
    public ProfileImage deleteById(@NonNull String id) {
        var imageFromDbOpt = imageRepository.findById(id);
        if (imageFromDbOpt.isEmpty())
            return null;
        imageRepository.deleteById(id);
        return imageFromDbOpt.get();
    }

}
