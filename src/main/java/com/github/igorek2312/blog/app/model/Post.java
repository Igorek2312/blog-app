package com.github.igorek2312.blog.app.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Igor Rybak
 */
@Entity
@Table(name = "post")
public class Post implements PostOwner {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Length(min = 1, max = 50)
    @NotEmpty
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @NotBlank
    @Column(name = "content", columnDefinition = "MEDIUMTEXT", nullable = false)
    private String content;

    @Column(name = "date_time_published", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime dateTimePublished;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Override
    public boolean isUserPostOwner(String username) {
        return user.getUsername().equals(username);
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTimePublished() {
        return dateTimePublished;
    }

    public void setDateTimePublished(LocalDateTime dateTimePublished) {
        this.dateTimePublished = dateTimePublished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
