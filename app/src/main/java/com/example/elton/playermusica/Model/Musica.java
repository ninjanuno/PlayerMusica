package com.example.elton.playermusica.Model;

import android.media.Image;

import java.io.Serializable;
import java.util.Date;

public class Musica implements Serializable {
    private long id;

    private String title;

    private int duration;

    private Album album;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
