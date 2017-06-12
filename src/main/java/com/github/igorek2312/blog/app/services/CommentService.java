package com.github.igorek2312.blog.app.services;

import com.github.igorek2312.blog.app.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Igor Rybak
 */
public interface CommentService {
    @Query("select c from Comment c where c.post.id=:postId order by c.dateTimeCommented")
    Page<Comment> getByPostId(int postId, Pageable pageable);

    void createComment(Comment comment, int postId);

    void deleteComment(int commentId);
}
