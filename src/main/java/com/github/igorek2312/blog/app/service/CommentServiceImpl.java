package com.github.igorek2312.blog.app.service;

import com.github.igorek2312.blog.app.error.ForbiddenException;
import com.github.igorek2312.blog.app.model.Comment;
import com.github.igorek2312.blog.app.model.Post;
import com.github.igorek2312.blog.app.repository.CommentRepository;
import com.github.igorek2312.blog.app.repository.PostRepository;
import com.github.igorek2312.blog.app.repository.UserRepository;
import com.github.igorek2312.blog.app.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.github.igorek2312.blog.app.utils.EntityUtils.retrieveOneOrThrowNotFound;
import static com.github.igorek2312.blog.app.utils.SecurityUtils.isCurrentUserOwner;

/**
 * @author Igor Rybak
 */
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Page<Comment> getByPostId(int postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    @Override
    public void createComment(Comment comment, int postId) {
        String username = SecurityUtils.getCurrentUsername();
        userRepository.findByUsername(username).ifPresent(comment::setUser);
        Post post = retrieveOneOrThrowNotFound(postRepository::findOne, postId, Post.class);
        comment.setPost(post);
        comment.setDateTimeCommented(LocalDateTime.now());
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        Comment comment = retrieveOneOrThrowNotFound(commentRepository::findOne, commentId, Comment.class);
        if (!isCurrentUserOwner(comment::isUserPostOwner)) throw new ForbiddenException();
        commentRepository.delete(commentId);
    }
}
