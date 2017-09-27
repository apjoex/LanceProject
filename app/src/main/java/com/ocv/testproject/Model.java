package com.ocv.testproject;

import java.io.Serializable;

/**
 * Created by lancelittle on 9/25/17.
 */

public class Model implements Serializable {
    private String id, creator, date_sec, date_usec, title, content, blogId, images, status;


    public Model(String id, String creator, String date_sec, String date_usec, String title, String content, String blogId, String images, String status) {
        this.id = id;
        this.creator = creator;
        this.date_sec = date_sec;
        this.date_usec = date_usec;
        this.title = title;
        this.content = content;
        this.blogId = blogId;
        this.images = images;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDate_sec() {
        return date_sec;
    }

    public void setDate_sec(String date_sec) {
        this.date_sec = date_sec;
    }

    public String getDate_usec() {
        return date_usec;
    }

    public void setDate_usec(String date_usec) {
        this.date_usec = date_usec;
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

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
