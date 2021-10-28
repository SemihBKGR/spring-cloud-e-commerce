package com.semihbkgr.ecommerce.imageserver.content;

import lombok.Getter;
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
@Getter
@Slf4j
public class LocalImageContentProvider implements ImageContentProvider {

    private final String name;
    private final Path rootDirPath;

    @Override
    public void save(@NonNull String filename, @NonNull MultipartFile file) throws IOException {
        transferFile(rootDirPath.resolve(filename), file);
    }

    @Override
    public void save(@NonNull String dirPath, @NonNull String filename, @NonNull MultipartFile file) throws IOException {
        var path = rootDirPath.resolve(dirPath);
        if (Files.notExists(path))
            Files.createDirectories(path);
        transferFile(rootDirPath.resolve(dirPath).resolve(filename), file);
    }

    @Override
    public byte[] get(@NonNull String filename) throws IOException {
        return readFile(rootDirPath.resolve(filename));
    }

    @Override
    public byte[] get(@NonNull String dirPath, @NonNull String filename) throws IOException {
        return readFile(rootDirPath.resolve(dirPath).resolve(filename));
    }

    @Override
    public void delete(@NonNull String filename) throws IOException {
        deleteFile(rootDirPath.resolve(filename));
    }

    @Override
    public void delete(@NonNull String dirPath, @NonNull String filename) throws IOException {
        deleteFile(rootDirPath.resolve(dirPath).resolve(filename));
    }

    public void createRootDirIfNotExists() throws IOException {
        if (!Files.exists(rootDirPath)) {
            log.info("{}, rootDirPath does not exist", name);
            Files.createDirectories(rootDirPath);
            log.info("{}, rootDirPath has been created successfully", name);
        }
        log.info("{}, rootDirPath: {}", name, rootDirPath);
    }

    private void transferFile(@NonNull Path path, @NonNull MultipartFile multipartFile) throws IOException {
        multipartFile.transferTo(path);
        log.info("{}, file has been transferred, path: {}", name, path);
    }

    private byte[] readFile(@NonNull Path path) throws IOException {
        if (!Files.exists(path))
            throw new IllegalStateException();
        try (var b = new BufferedInputStream(new FileInputStream(path.toFile()))) {
            return b.readAllBytes();
        } catch (FileNotFoundException e) {
            throw new IOException(e);
        }
    }

    private void deleteFile(@NonNull Path path) throws IOException {
        if (!Files.exists(path))
            throw new IllegalStateException();
        Files.delete(path);
        log.info("{}, file has been deleted, path: {}", name, path);
    }

}
