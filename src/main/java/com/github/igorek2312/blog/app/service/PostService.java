package com.github.igorek2312.blog.app.service;

import com.github.igorek2312.blog.app.model.Post;
import com.github.igorek2312.blog.app.transfer.PostListItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author Igor Rybak
 */
public interface PostService {

    Post getById(int postId);

    Page<PostListItem> getByUserId(int userId, Pageable pageable);

    void create(Post post);

    void update(int postId, Post post);

    void delete(int postId);
}
