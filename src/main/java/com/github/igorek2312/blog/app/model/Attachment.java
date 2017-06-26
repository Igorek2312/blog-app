package com.github.igorek2312.blog.app.model;

import javax.persistence.*;

/**
 * @author Igor Rybak
 */
@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
