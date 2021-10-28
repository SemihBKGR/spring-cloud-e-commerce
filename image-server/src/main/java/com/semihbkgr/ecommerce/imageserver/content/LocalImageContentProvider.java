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

    private final Path rootDirPath;

    @Override
    public void save(@NonNull String id, @NonNull MultipartFile file) throws IOException {
        file.transferTo(rootDirPath.resolve(id));
    }

    @Override
    public void update(@NonNull String id, @NonNull MultipartFile file) throws IOException {
        var path = rootDirPath.resolve(id);
        if (!Files.exists(path))
            throw new IllegalStateException();
        file.transferTo(path);
    }

    @Override
    public byte[] get(@NonNull String id) throws IOException {
        var path = rootDirPath.resolve(id);
        if (!Files.exists(path))
            throw new IllegalStateException();
        try (var b = new BufferedInputStream(new FileInputStream(path.toFile()))) {
            return b.readAllBytes();
        } catch (FileNotFoundException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void delete(@NonNull String id) throws IOException {
        var path = rootDirPath.resolve(id);
        if (!Files.exists(path))
            throw new IllegalStateException();
        Files.delete(path);
    }

    public void createRootDirIfNotExists() throws IOException {
        if (!Files.exists(rootDirPath)) {
            Files.createDirectories(rootDirPath);
        }
    }

    public void clearRootDir() throws IOException {
        if (Files.exists(rootDirPath)) {
            Files.delete(rootDirPath);
        }
    }

}
