package com.github.igorek2312.blog.app.web;

import com.github.igorek2312.blog.app.model.Comment;
import com.github.igorek2312.blog.app.services.CommentService;
import com.github.igorek2312.blog.app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
