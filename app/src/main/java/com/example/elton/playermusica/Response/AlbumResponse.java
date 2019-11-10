package com.example.elton.playermusica.Response;

import com.example.elton.playermusica.Model.AlbumAmpliado;

import java.util.List;

public class AlbumResponse {
    private List<AlbumAmpliado> data;

    public List<AlbumAmpliado> getData() {
        return data;
    }

    public void setData(List<AlbumAmpliado> data) {
        this.data = data;
    }
}
