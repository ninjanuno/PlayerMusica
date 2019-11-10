package com.example.elton.playermusica.Response;

import com.example.elton.playermusica.Model.Musica;

import java.util.List;

public class SearchMusicaResponse {
    private List<Musica> data;


    public List<Musica> getData() {
        return data;
    }

    public void setData(List<Musica> data) {
        this.data = data;
    }
}
