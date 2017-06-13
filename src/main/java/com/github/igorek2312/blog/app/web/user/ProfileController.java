package com.github.igorek2312.blog.app.web.user;

import com.github.igorek2312.blog.app.model.User;
import com.github.igorek2312.blog.app.services.UserService;
import com.github.igorek2312.blog.app.transfer.user.UpdateEmailForm;
import com.github.igorek2312.blog.app.transfer.user.UpdateProfileForm;
import com.github.igorek2312.blog.app.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Igor Rybak
 */
@Controller
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/my-profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String myProfile() {
        String username = SecurityUtils.getCurrentUsername();
        return "redirect:/profiles/" + username;
    }

    @GetMapping("/profiles/{username}")
    public String profile(
            @PathVariable("username") String username,
            Model model
    ) {
        User user = userService.getByUsername(username);
        model.addAttribute("user", user);
        String currentUsername = SecurityUtils.getCurrentUsername();
        model.addAttribute("isCurrentUserOwner", username.equals(currentUsername));
        return "user/profile";
    }

    @GetMapping("/edit-my-profile")
    public String getProfileToEdit(Model model) {
        String username = SecurityUtils.getCurrentUsername();
        User user = userService.getByUsername(username);
        model.addAttribute("user", user);
        return "user/edit-profile";
    }

    @PostMapping("/update-email")
    public String updateEmail(
            @ModelAttribute("user") @Validated UpdateEmailForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "user/edit-profile";
        }
        userService.updateEmail(form.getEmail());
        return "redirect:/edit-my-profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(
            @ModelAttribute("user") @Validated UpdateProfileForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "user/edit-profile";
        }
        userService.update(form.getFirstName(), form.getLastName());
        return "redirect:/edit-my-profile";
    }

    @PostMapping("/change-profile-image")
    public String changeProfileImage(@RequestParam("file") MultipartFile file){
        return "redirect:/edit-my-profile";
    }
}
