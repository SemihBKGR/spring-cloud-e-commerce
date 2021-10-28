package com.semihbkgr.ecommerce.imageserver.content;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageContentProvider {

    void save(String filename, MultipartFile file) throws IOException;

    void save(String dirPath,String filename, MultipartFile file) throws IOException;

    byte[] get(String filename) throws IOException;

    byte[] get(String dirPath,String filename) throws IOException;

    void delete(String filename) throws IOException;

    void delete(String dirPath,String filename) throws IOException;

}
