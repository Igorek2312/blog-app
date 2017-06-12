package com.github.igorek2312.blog.app.transfer;

import java.time.LocalDateTime;

/**
 * @author Igor Rybak
 */
public class PostListItem {
    private final int id;
    private final String title;

    private final LocalDateTime dateTimePublished;

    public PostListItem(int id, String title, LocalDateTime dateTimePublished) {
        this.id = id;
        this.title = title;
        this.dateTimePublished = dateTimePublished;
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
}
