package com.semihbkgr.ecommerce.imageserver.content;

import org.springframework.core.io.buffer.DataBuffer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ImageContentProvider {

    Mono<Void> save(String id, Flux<DataBuffer> content);

    Mono<Void> update(String id, Flux<DataBuffer> content);

    Flux<DataBuffer> get(String id);

    Mono<Void> delete(String id);

}
