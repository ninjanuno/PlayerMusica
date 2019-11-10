package com.example.elton.playermusica.Model;

import java.io.Serializable;

public class MusicaAmpliado extends Musica implements Serializable {


    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public int getDuration() {
        return super.getDuration();
    }

    @Override
    public void setDuration(int duration) {
        super.setDuration(duration);
    }

    @Override
    public Album getAlbum() {
        return super.getAlbum();
    }

    @Override
    public void setAlbum(Album album) {
        super.setAlbum(album);
    }
}
