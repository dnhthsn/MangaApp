package com.example.mangaapp.model;

import java.io.Serializable;

public class Mangas implements Serializable {
    private String imgManga, nameManga;
    private String chapters;

    public Mangas() {
    }

    public Mangas(String imgManga, String nameManga, String chapters) {
        this.imgManga = imgManga;
        this.nameManga = nameManga;
        this.chapters = chapters;
    }

    public String getImgManga() {
        return imgManga;
    }

    public void setImgManga(String imgManga) {
        this.imgManga = imgManga;
    }

    public String getNameManga() {
        return nameManga;
    }

    public void setNameManga(String nameManga) {
        this.nameManga = nameManga;
    }

    public String getChapters() {
        return chapters;
    }

    public void setChapters(String chapters) {
        this.chapters = chapters;
    }
}
