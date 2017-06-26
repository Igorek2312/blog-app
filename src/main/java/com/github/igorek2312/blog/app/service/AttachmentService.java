package com.github.igorek2312.blog.app.service;

import com.github.igorek2312.blog.app.model.Attachment;

import java.util.List;

/**
 * @author Igor Rybak
 */
public interface AttachmentService {
    void create(String fileUrl, int postId);

    List<Attachment> getFiles(int postId);

    List<Attachment> getImages(List<Attachment> attachments);

    List<Attachment> getNotImages(List<Attachment> attachments);

    String getUrl(int attachmentId);

    void delete(int attachmentId);
}
