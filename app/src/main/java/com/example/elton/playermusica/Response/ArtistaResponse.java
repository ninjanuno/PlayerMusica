package com.example.elton.playermusica.Response;

import com.example.elton.playermusica.Model.ArtistaAmpliado;

import java.util.List;

public class ArtistaResponse {
    private List<ArtistaAmpliado> data;


    public List<ArtistaAmpliado> getData() {
        return data;
    }

    public void setData(List<ArtistaAmpliado> data) {
        this.data = data;
    }
}
