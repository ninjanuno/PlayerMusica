package com.example.elton.playermusica.Service;

import com.example.elton.playermusica.Response.AlbumResponse;
import com.example.elton.playermusica.Response.ArtistaResponse;
import com.example.elton.playermusica.Response.MusicaResponse;
import com.example.elton.playermusica.Response.SearchAlbumResponse;
import com.example.elton.playermusica.Response.SearchArtistaResponse;
import com.example.elton.playermusica.Response.SearchMusicaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchService {
    @GET("/search/artist")
    public Call<SearchArtistaResponse> searchArtista(@Query("q") String query);

    @GET("/search/album")
    public Call<SearchAlbumResponse> searchAlbum(@Query("q") String query);

    @GET("/search/track")
    public Call<SearchMusicaResponse> searchMusica(@Query("q") String query);

    @GET("/artist/{id}")
    public Call<ArtistaResponse> artistaDetalhado(@Path("id") String id);

    @GET("/artist/{id}/albums")
    public Call<AlbumResponse> albumDetalhado(@Path("id") String id);

    @GET("/album/{id}/tracks")
    public Call<MusicaResponse> musicaDetalhado(@Path("id") String id);

    @GET("/album/{id}")
    public Call<MusicaResponse> albumDetalhado2(@Path("id") String id);

}
