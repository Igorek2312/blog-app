package com.github.igorek2312.blog.app.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static java.io.File.separator;

/**
 * @author Igor Rybak
 */
@Service
public class CloudinaryStorageService implements StorageService {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryStorageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String save(MultipartFile multipartFile) throws IOException {
        String pathname = System.getProperty("java.io.tmpdir") + separator + multipartFile.getOriginalFilename();
        File file = new File(pathname);
        multipartFile.transferTo(file);

        Map map = cloudinary.uploader()
                .upload(
                        file,
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "original_filename", multipartFile.getOriginalFilename()
                        )
                );
        return (String) map.get("secure_url");
    }

    private Optional<String> getClodinaryId(URL url) {
        String host = url.getHost();
        String path = url.getPath();
        if (!host.equals("res.cloudinary.com")) return Optional.empty();
        String id = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
        return Optional.of(id);
    }

    @Override
    public void deleteImage(String publicId) {
        try {
            cloudinary.api().deleteResources(Arrays.asList(publicId), ObjectUtils.emptyMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteImageByUrl(String url) {
        try {
            URL imageUrl = new URL(url);
            getClodinaryId(imageUrl).ifPresent(this::deleteImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
