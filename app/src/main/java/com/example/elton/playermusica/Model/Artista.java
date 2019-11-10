package com.example.elton.playermusica.Model;

import android.media.Image;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Artista implements Serializable {

    private long id;

    private String name;

    private String picture;

    @JsonProperty("nb_album")
    private int numeroAlbuns;

    @JsonProperty("nb_fan")
    private int numeroFas;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getNumeroAlbuns() {
        return numeroAlbuns;
    }

    public void setNumeroAlbuns(int numeroAlbuns) {
        this.numeroAlbuns = numeroAlbuns;
    }

    public int getNumeroFas() {
        return numeroFas;
    }

    public void setNumeroFas(int numeroFas) {
        this.numeroFas = numeroFas;
    }

}
