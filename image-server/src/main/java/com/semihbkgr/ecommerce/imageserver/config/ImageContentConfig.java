package com.semihbkgr.ecommerce.imageserver.config;

import com.semihbkgr.ecommerce.imageserver.content.LocalImageContentProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class ImageContentConfig {

    @Value("${image.content.profile.root-dir-path}")
    private String profileImageContentRootDirPath;

    @Value("${image.content.production.root-dir-path}")
    private String productionImageContentRootDirPath;

    @Bean(name = "profileImageContentProvider",
            initMethod = "createRootDirIfNotExists",
            destroyMethod = "clearRootDir")
    public LocalImageContentProvider profileLocalImageContentProvider() {
        return new LocalImageContentProvider(Path.of(profileImageContentRootDirPath));
    }

    @Bean(name = "productionImageContentProvider",
            initMethod = "createRootDirIfNotExists",
            destroyMethod = "clearRootDir")
    public LocalImageContentProvider productionLocalImageContentProvider() {
        return new LocalImageContentProvider(Path.of(productionImageContentRootDirPath));
    }

}
