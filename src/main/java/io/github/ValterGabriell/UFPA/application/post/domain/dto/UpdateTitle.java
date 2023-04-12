package io.github.ValterGabriell.UFPA.application.post.domain.dto;

public class UpdateTitle {
    private String title;

    public UpdateTitle() {
    }

    public UpdateTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
