package com.example.elton.playermusica.Response;

import com.example.elton.playermusica.Model.Artista;

import java.util.List;

public class SearchArtistaResponse {


    private List<Artista> data;


    public List<Artista> getData() {
        return data;
    }

    public void setData(List<Artista> data) {
        this.data = data;
    }

}
