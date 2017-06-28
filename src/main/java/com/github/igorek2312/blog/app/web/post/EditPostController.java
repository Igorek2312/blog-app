package com.github.igorek2312.blog.app.web.post;

import com.github.igorek2312.blog.app.model.Attachment;
import com.github.igorek2312.blog.app.model.Post;
import com.github.igorek2312.blog.app.service.AttachmentService;
import com.github.igorek2312.blog.app.service.PostService;
import com.github.igorek2312.blog.app.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Igor Rybak
 */
@Controller
public class EditPostController {
    private final PostService postService;
    private final AttachmentService attachmentService;
    private final StorageService storageService;

    @Autowired
    public EditPostController(
            PostService postService,
            AttachmentService attachmentService,
            StorageService storageService
    ) {
        this.postService = postService;
        this.attachmentService = attachmentService;
        this.storageService = storageService;
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/posts/{postId}/delete-attached-image/{attachmentId}")
    public String deleteAttachedImage(
            @PathVariable int attachmentId
    ) {
        String url = attachmentService.getUrl(attachmentId);
        attachmentService.delete(attachmentId);
        storageService.deleteImageByUrl(url);
        return "redirect:/edit-posts/{postId}";
    }
}
