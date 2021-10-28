package com.semihbkgr.ecommerce.imageserver.content;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageContentProvider {

    void save(String id, MultipartFile content) throws IOException;

    void update(String id, MultipartFile content) throws IOException;

    byte[] get(String id) throws IOException;

    void delete(String id) throws IOException;

}
