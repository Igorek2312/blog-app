package com.github.igorek2312.blog.app.service;

import com.github.igorek2312.blog.app.error.ForbiddenException;
import com.github.igorek2312.blog.app.model.Post;
import com.github.igorek2312.blog.app.repository.PostRepository;
import com.github.igorek2312.blog.app.repository.UserRepository;
import com.github.igorek2312.blog.app.transfer.PostListItem;
import com.github.igorek2312.blog.app.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.github.igorek2312.blog.app.utils.EntityUtils.retrieveOneOrThrowNotFound;
import static com.github.igorek2312.blog.app.utils.SecurityUtils.isCurrentUserOwner;

/**
 * @author Igor Rybak
 */
@Service
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(
            PostRepository postRepository,
            UserRepository userRepository
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Post getById(int postId) {
        return retrieveOneOrThrowNotFound(postRepository::findOne, postId, Post.class);
    }

    @Override
    public Page<PostListItem> getByUserId(int userId, Pageable pageable) {
        return postRepository.findByUserId(userId, pageable);
    }

    @Override
    public Page<PostListItem> getAll(Pageable pageable) {
        return postRepository.findAllPosts(pageable);
    }

    @Override
    public void create(Post post) {
        String username = SecurityUtils.getCurrentUsername();
        userRepository.findByUsername(username).ifPresent(post::setUser);
        post.setDateTimePublished(LocalDateTime.now());

        postRepository.save(post);
    }

    @Transactional
    @Override
    public void update(int postId, Post post) {
        postRepository.update(postId, post.getTitle(), post.getContent());
    }

    @Override
    public void delete(int postId) {
        Post post = retrieveOneOrThrowNotFound(postRepository::findOne, postId, Post.class);
        if (!isCurrentUserOwner(post::isUserPostOwner)) throw new ForbiddenException();
        postRepository.delete(postId);
    }

}
