package com.github.igorek2312.blog.app.repository;

import com.github.igorek2312.blog.app.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Igor Rybak
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select c from Comment c where " +
            "c.post.id=:postId " +
            "order by c.dateTimeCommented desc")
    Page<Comment> findByPostId(@Param("postId") int postId, Pageable pageable);
}