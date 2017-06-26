package com.github.igorek2312.blog.app.web;

import com.github.igorek2312.blog.app.service.CommentService;
import com.github.igorek2312.blog.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Igor Rybak
 */
@Controller
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public CommentController(
            CommentService commentService,
            PostService postService
    ) {
        this.commentService = commentService;
        this.postService = postService;
    }


}
