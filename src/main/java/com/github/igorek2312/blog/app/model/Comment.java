package com.github.igorek2312.blog.app.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Igor Rybak
 */
@Entity
@Table(name = "comment")
public class Comment implements PostOwner, CommentOwner {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Length(min = 1, max = 300)
    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "date_time_commented", columnDefinition = "DATETIME",nullable = false)
    private LocalDateTime dateTimeCommented;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public LocalDateTime getDateTimeCommented() {
        return dateTimeCommented;
    }

    public void setDateTimeCommented(LocalDateTime dateTimeCommented) {
        this.dateTimeCommented = dateTimeCommented;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean isUserPostOwner(String username) {
        return post.getUser().getUsername().equals(username);
    }

    @Override
    public boolean isUserCommentOwner(String username) {
        return user.getUsername().equals(username);
    }
}
