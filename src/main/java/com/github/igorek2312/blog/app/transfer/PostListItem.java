package com.github.igorek2312.blog.app.transfer;

import java.time.LocalDateTime;

/**
 * @author Igor Rybak
 */
public class PostListItem {
    private final int id;
    private final String title;
    private final LocalDateTime dateTimePublished;
    private final String userFullName;
    private final String username;

    public PostListItem(int id, String title, LocalDateTime dateTimePublished, String userFullName, String username) {
        this.id = id;
        this.title = title;
        this.dateTimePublished = dateTimePublished;
        this.userFullName = userFullName;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDateTimePublished() {
        return dateTimePublished;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getUsername() {
        return username;
    }
}
