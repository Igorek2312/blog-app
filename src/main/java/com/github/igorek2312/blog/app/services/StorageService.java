package com.github.igorek2312.blog.app.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Igor Rybak
 */
public interface StorageService {
    String save(MultipartFile multipartFile) throws IOException;

    void deleteImageByUrl(String url);
}
