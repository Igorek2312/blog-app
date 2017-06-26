package com.github.igorek2312.blog.app.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

/**
 * @author Igor Rybak
 */
@Component
public class MediaTypeResolverImpl implements MediaTypeResolver {

    private static boolean isRedirect(int statusCode) {
        return statusCode == HttpURLConnection.HTTP_MOVED_TEMP
                || statusCode == HttpURLConnection.HTTP_MOVED_PERM
                || statusCode == HttpURLConnection.HTTP_SEE_OTHER;
    }

    private Optional<MediaType> getContentType(HttpURLConnection urlConnection) {
        try {
            urlConnection.setRequestMethod("HEAD");
            if (isRedirect(urlConnection.getResponseCode())) {
                String newUrl = urlConnection.getHeaderField("Location");
                return resolve(newUrl);
            }
            return Optional.ofNullable(urlConnection.getContentType())
                    .map(MediaType::parseMediaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<MediaType> resolve(String urlString) {
        try {
            return Optional.of(new URL(urlString).openConnection())
                    .map(c -> c instanceof HttpURLConnection ? (HttpURLConnection) c : null)
                    .flatMap(this::getContentType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isImage(String url) {
        return resolve(url)
                .map(mediaType -> mediaType.toString().startsWith("image/"))
                .orElse(false);
    }
}
