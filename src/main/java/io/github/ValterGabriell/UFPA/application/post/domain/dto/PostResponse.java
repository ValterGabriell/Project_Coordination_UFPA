package io.github.ValterGabriell.UFPA.application.post.domain.dto;

import java.time.LocalDate;

public class PostResponse {

    private String postId;
    private String body;
    private String title;
    private LocalDate postedAt;
    private String link;

    private String imgRef;


    public PostResponse(String postId, String body, String title, LocalDate postedAt, String link, String imgRef) {
        this.postId = postId;
        this.body = body;
        this.title = title;
        this.postedAt = postedAt;
        this.link = link;
        this.imgRef = imgRef;
    }

    public PostResponse() {
    }

    public String getImgRef() {
        return imgRef;
    }

    public void setImgRef(String imgRef) {
        this.imgRef = imgRef;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDate postedAt) {
        this.postedAt = postedAt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
