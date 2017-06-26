package com.github.igorek2312.blog.app.web;

import com.github.igorek2312.blog.app.model.Attachment;
import com.github.igorek2312.blog.app.model.Comment;
import com.github.igorek2312.blog.app.model.Post;
import com.github.igorek2312.blog.app.model.User;
import com.github.igorek2312.blog.app.service.*;
import com.github.igorek2312.blog.app.transfer.PostListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.github.igorek2312.blog.app.utils.SecurityUtils.getCurrentUser;
import static com.github.igorek2312.blog.app.utils.SecurityUtils.isCurrentUserOwner;

/**
 * @author Igor Rybak
 */
@Controller
public class PostController {
    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;
    private final AttachmentService attachmentService;
    private final StorageService storageService;

    @Autowired
    public PostController(PostService postService, CommentService commentService, UserService userService, AttachmentService attachmentService, StorageService storageService) {
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
        this.attachmentService = attachmentService;
        this.storageService = storageService;
    }

    private void initModel(Model model, int postId, Pageable pageable) {
        Post post = postService.getById(postId);
        model.addAttribute("post", post);
        boolean isOwner = isCurrentUserOwner(post::isUserPostOwner);
        model.addAttribute("isCurrentUserOwner", isOwner);

        Page<Comment> comments = commentService.getByPostId(postId, pageable);
        model.addAttribute("comments", comments);

        List<Attachment> files = attachmentService.getFiles(postId);
        List<Attachment> notImages = attachmentService.getNotImages(files);
        List<Attachment> images = attachmentService.getImages(files);
        model.addAttribute("files", notImages);
        model.addAttribute("images", images);
    }

    @GetMapping("/posts/{postId}")
    public String getPost(
            Model model,
            @PathVariable int postId,
            Pageable pageable
    ) {
        initModel(model, postId, pageable);
        model.addAttribute("comment", new Comment());
        return "post/post-details";
    }

    @GetMapping("/my-posts")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getMyPosts() {
        User currentUser = getCurrentUser(User.class).get();
        return "redirect:/users/" + currentUser.getId() + "/posts";
    }

    @GetMapping("/users/{userId}/posts")
    public String getUserPosts(
            @PathVariable int userId,
            @PageableDefault(size = 10) Pageable pageable,
            Model model
    ) {
        Page<PostListItem> posts = postService.getByUserId(userId, pageable);
        model.addAttribute("posts", posts);
        User user = userService.getById(userId);
        model.addAttribute("user", user);

        Boolean isOwner = getCurrentUser(User.class)
                .map(User::getId)
                .map(id -> id.equals(userId))
                .orElse(false);

        model.addAttribute("isCurrentUserOwner", isOwner);
        return "post/user-posts";
    }

    @GetMapping("/create-post")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getCreatePostForm(
            Model model
    ) {
        model.addAttribute("post", new Post());
        return "post/create-post";
    }

    @PostMapping("/posts")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String createPost(
            @ModelAttribute("post") @Validated Post post,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "post/create-post";
        }

        postService.create(post);
        return "redirect:/my-posts";
    }

    @GetMapping("/edit-post/{postId}")
    public String getEditPostForm(
            @PathVariable int postId,
            Model model
    ) {
        Post post = postService.getById(postId);
        model.addAttribute("post", post);
        List<Attachment> files = attachmentService.getFiles(postId);
        List<Attachment> images = attachmentService.getImages(files);
        model.addAttribute("images", images);

        return "post/edit-post";
    }

    @PostMapping("/edit-post/{postId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String editPost(
            @PathVariable int postId,
            @ModelAttribute("post") @Validated Post post,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            post.setId(postId);
            return "post/edit-post";
        }

        postService.update(postId, post);
        return "redirect:/edit-post/{postId}";
    }

    @GetMapping("/delete-post/{postId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String deletePost(@PathVariable int postId) {
        postService.delete(postId);
        return "redirect:/my-posts";
    }

    @PostMapping("/posts/{postId}/comments")
    public String createComment(
            @ModelAttribute("comment") @Validated Comment comment,
            BindingResult result,
            Model model,
            @PathVariable int postId,
            Pageable pageable
    ) {
        if (result.hasErrors()) {
            initModel(model, postId, pageable);
            return "post/post-details";
        }

        commentService.createComment(comment, postId);

        return "redirect:/posts/{postId}";
    }

    @GetMapping("/posts/{postId}/delete-comment/{commentId}")
    public String deleteComment(
            @PathVariable int commentId
    ) {
        commentService.deleteComment(commentId);
        return "redirect:/posts/{postId}";
    }

    @PostMapping("/posts/{postId}/attach-file")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String attachFile(
            @PathVariable int postId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        String fileUrl = storageService.save(file);
        attachmentService.create(fileUrl, postId);
        return "redirect:/posts/{postId}";
    }

    private void deleteAttachment(@PathVariable int attachmentId) {
        String url = attachmentService.getUrl(attachmentId);
        attachmentService.delete(attachmentId);
        storageService.deleteImageByUrl(url);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/posts/{postId}/delete-attached-image/{attachmentId}")
    public String deleteAttachedImage(
            @PathVariable int attachmentId
    ) {
        deleteAttachment(attachmentId);
        return "redirect:/edit-posts/{postId}";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/posts/{postId}/delete-attached-file/{attachmentId}")
    public String deleteAttachedFile(
            @PathVariable int attachmentId
    ) {
        deleteAttachment(attachmentId);
        return "redirect:/posts/{postId}";
    }
}