package com.example.elton.playermusica.Response;

import com.example.elton.playermusica.Model.Album;

import java.util.List;

public class SearchAlbumResponse {
    private List<Album> data;


    public List<Album> getData() {
        return data;
    }

    public void setData(List<Album> data) {
        this.data = data;
    }
}
