package io.github.ValterGabriell.UFPA.application.post.domain;


import io.github.ValterGabriell.UFPA.application.post.domain.dto.PostResponse;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Post {

    @Id
    @Column(name = "post_id", nullable = false)
    private String postId;

    @Column(name = "post_body", nullable = false)
    private String body;

    @Column(name = "post_title", nullable = false)
    private String title;

    @Temporal(TemporalType.DATE)
    private LocalDate postedAt;

    @Column(name = "post_img_ref", length = 64)
    private String imgRef;

    @Column(name = "post_link")
    private String link;


    public Post() {
    }

    public Post(String body, String title, String imgRef, String link) {
        this.body = body;
        this.title = title;
        this.imgRef = imgRef;
        this.link = link;
    }

    public PostResponse toPostResponse() {
        return new PostResponse(this.postId, this.body, this.title, this.postedAt, this.link, this.imgRef);
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

    public String getImgRef() {
        return imgRef;
    }

    public void setImgRef(String imgRef) {
        this.imgRef = imgRef;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
