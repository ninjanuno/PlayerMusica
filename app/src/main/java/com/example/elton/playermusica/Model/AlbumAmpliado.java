package com.example.elton.playermusica.Model;

import java.io.Serializable;
import java.util.List;

public class AlbumAmpliado extends Album implements Serializable {

    private int fans;

    private int duration;

    private List<Musica> track;

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Musica> getTrack() {
        return track;
    }

    public void setTrack(List<Musica> track) {
        this.track = track;
    }

}
