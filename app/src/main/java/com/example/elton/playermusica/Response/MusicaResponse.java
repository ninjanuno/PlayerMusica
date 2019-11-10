package com.example.elton.playermusica.Response;

import com.example.elton.playermusica.Model.MusicaAmpliado;

import java.util.List;

public class MusicaResponse {
    private List<MusicaAmpliado> data;


    public List<MusicaAmpliado> getData() {
        return data;
    }

    public void setData(List<MusicaAmpliado> data) {
        this.data = data;
    }
}
