package com.semihbkgr.ecommerce.imageserver.content;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor
@Slf4j
public class LocalImageContentProvider implements ImageContentProvider {

    private final String name;
    private final Path rootDirPath;

    @Override
    public void save(@NonNull String filename, @NonNull MultipartFile file) throws IOException {
        file.transferTo(rootDirPath.resolve(filename));
    }

    @Override
    public byte[] get(@NonNull String filename) throws IOException {
        var path = rootDirPath.resolve(filename);
        if (!Files.exists(path))
            throw new IllegalStateException();
        try (var b = new BufferedInputStream(new FileInputStream(path.toFile()))) {
            return b.readAllBytes();
        } catch (FileNotFoundException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void delete(@NonNull String filename) throws IOException {
        var path = rootDirPath.resolve(filename);
        if (!Files.exists(path))
            throw new IllegalStateException();
        Files.delete(path);
    }

    public Path getRootDirPath() {
        return rootDirPath;
    }

    public String getName() {
        return name;
    }

    public void createRootDirIfNotExists() throws IOException {
        if (!Files.exists(rootDirPath)) {
            log.info("{}, rootDirPath does not exist",name);
            Files.createDirectories(rootDirPath);
            log.info("{}, rootDirPath has been created successfully",name);
        }
        log.info("{}, rootDirPath: {}",name,rootDirPath);
    }

}
