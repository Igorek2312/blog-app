package com.github.igorek2312.blog.app.service;

import org.springframework.http.MediaType;

import java.util.Optional;

/**
 * @author Igor Rybak
 */
public interface MediaTypeResolver {
    Optional<MediaType> resolve(String url);

    boolean isImage(String url);
}
