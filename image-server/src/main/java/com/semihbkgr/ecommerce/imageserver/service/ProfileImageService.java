package com.semihbkgr.ecommerce.imageserver.service;

import com.semihbkgr.ecommerce.modelcommon.image.ProfileImage;

public interface ProfileImageService {

    ProfileImage save(ProfileImage image);

    ProfileImage findById(String id);

    ProfileImage deleteById(String id);

}
