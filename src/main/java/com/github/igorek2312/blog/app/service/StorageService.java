package com.github.igorek2312.blog.app.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Igor Rybak
 */
public interface StorageService {
    String save(MultipartFile multipartFile) throws IOException;

    void deleteImage(String publicId);

    void deleteImageByUrl(String url);
}
