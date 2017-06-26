package com.github.igorek2312.blog.app.service;

import com.github.igorek2312.blog.app.model.Attachment;
import com.github.igorek2312.blog.app.model.Post;
import com.github.igorek2312.blog.app.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Igor Rybak
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final MediaTypeResolver mediaTypeResolver;

    @Autowired
    public AttachmentServiceImpl(
            AttachmentRepository attachmentRepository,
            MediaTypeResolver mediaTypeResolver
    ) {
        this.attachmentRepository = attachmentRepository;
        this.mediaTypeResolver = mediaTypeResolver;
    }

    @Override
    public void create(String fileUrl, int postId) {
        Attachment attachment = new Attachment();
        attachment.setPost(new Post(postId));
        attachment.setFileUrl(fileUrl);
        attachmentRepository.save(attachment);
    }

    @Override
    public List<Attachment> getFiles(int postId) {
        return attachmentRepository.findByPostId(postId);
    }

    @Override
    public List<Attachment> getImages(List<Attachment> attachments) {
        return attachments.stream()
                .filter(a -> mediaTypeResolver.isImage(a.getFileUrl()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Attachment> getNotImages(List<Attachment> attachments) {
        return attachments.stream()
                .filter(a -> !mediaTypeResolver.isImage(a.getFileUrl()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUrl(int attachmentId) {
        return attachmentRepository.getUrl(attachmentId);
    }

    @Override
    public void delete(int attachmentId) {
        attachmentRepository.delete(attachmentId);
    }
}
