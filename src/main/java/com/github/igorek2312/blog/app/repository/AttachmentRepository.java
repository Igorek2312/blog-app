package com.github.igorek2312.blog.app.repository;

import com.github.igorek2312.blog.app.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Igor Rybak
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    List<Attachment> findByPostId(int postId);

    @Query("select a.fileUrl from Attachment a where a.id=:attachmentId")
    String getUrl(@Param("attachmentId") int attachmentId);
}
