package io.github.ValterGabriell.UFPA.infra.api.dto;

public class Image {
    private String id;
    private String title;
    private String description;
    private int datetime;
    private String deletehash;
    private String name;
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDatetime() {
        return datetime;
    }

    public void setDatetime(int datetime) {
        this.datetime = datetime;
    }

    public String getDeletehash() {
        return deletehash;
    }

    public void setDeletehash(String deletehash) {
        this.deletehash = deletehash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Image(String id, String title, String description, int datetime, String deletehash, String name, String link) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.datetime = datetime;
        this.deletehash = deletehash;
        this.name = name;
        this.link = link;
    }

    public Image() {
    }
}
