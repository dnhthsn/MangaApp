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

    public String getNameManga() {
        return nameManga;
    }

    public String getChapters() {
        return chapters;
    }
}
