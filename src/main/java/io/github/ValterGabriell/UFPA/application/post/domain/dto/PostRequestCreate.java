package io.github.ValterGabriell.UFPA.application.post.domain.dto;


import io.github.ValterGabriell.UFPA.application.post.domain.Post;

public class PostRequestCreate {
    private String body;
    private String title;
    private String imgRef;
    private String link;

    public String getLink() {
        return link;
    }

    public PostRequestCreate(String body, String title, String imgRef, String link) {
        this.body = body;
        this.title = title;
        this.imgRef = imgRef;
        this.link = link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getImgRef() {
        return imgRef;
    }

    public void setImgRef(String imgRef) {
        this.imgRef = imgRef;
    }


    public Post toModel() {
        return new Post(this.body, this.title, this.imgRef, this.link);
    }
}
