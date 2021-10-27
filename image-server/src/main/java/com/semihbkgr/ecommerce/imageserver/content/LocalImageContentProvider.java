package com.semihbkgr.ecommerce.imageserver.content;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor
@Slf4j
public class LocalImageContentProvider implements ImageContentProvider {

    private final Path rootDirPath;

    @Override
    public Mono<Void> save(@NonNull String id, @NonNull Flux<DataBuffer> content) {
        return DataBufferUtils.write(content, rootDirPath.resolve(id));
    }

    @Override
    public Mono<Void> update(@NonNull String id, @NonNull Flux<DataBuffer> content) {
        return Mono.defer(() -> {
            var path = rootDirPath.resolve(id);
            if (Files.exists(path)) {
                return DataBufferUtils.write(content, rootDirPath);
            } else
                return Mono.empty();
        });
    }

    @Override
    public Flux<DataBuffer> get(@NonNull String id) {
        return DataBufferUtils.read(rootDirPath.resolve(id), new DefaultDataBufferFactory(), FileCopyUtils.BUFFER_SIZE);
    }

    @Override
    public Mono<Void> delete(@NonNull String id) {
        return Mono.defer(() -> {
            var path = rootDirPath.resolve(id);
            if (Files.exists(path)) {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    return Mono.error(e);
                }
            }
            return Mono.empty();
        });
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
