package com.example.elton.playermusica.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elton.playermusica.Adapter.ListaAlbumsDetalhadoAdapter;
import com.example.elton.playermusica.Adapter.ListaMusicasAdapter;
import com.example.elton.playermusica.Adapter.ListaMusicasDetalhadoAdapter;
import com.example.elton.playermusica.Model.Album;
import com.example.elton.playermusica.Model.Musica;
import com.example.elton.playermusica.Model.MusicaAmpliado;
import com.example.elton.playermusica.R;
import com.example.elton.playermusica.Response.MusicaResponse;
import com.example.elton.playermusica.Task.AlbumTask;
import com.example.elton.playermusica.Task.MusicaTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AlbumDetalhado extends AppCompatActivity implements View.OnClickListener, MusicaTask.MusicaTaskDelegate{
    public static final String PARAM_OBJETO = "PARAM_OBJETO";
    private List<MusicaAmpliado> musicas;
    public Album album2;
    private RecyclerView listaMusicasRecyclerView;
    private ListaMusicasDetalhadoAdapter listaMusicasAdapter;
    private TextView nomeAlbum,numeroMusicas;
    private ImageView fotoAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detalhado);
        nomeAlbum = findViewById(R.id.albumDetalhado_nome);
        fotoAlbum = findViewById(R.id.albumDetalhado_foto_album);
        numeroMusicas = findViewById(R.id.albumDetalhado_musicas_numero);

        //----------------------pega o put extra------------------------------------------------
        Intent intent = getIntent();
        if(intent.getSerializableExtra(PARAM_OBJETO) instanceof Album){
            Album album = (Album) intent.getSerializableExtra(PARAM_OBJETO);
            nomeAlbum.setText(album.getTitle());
            numeroMusicas.setText(String.valueOf(album.getNbTracks()));
            Picasso.with(this).load(album.getCover()).into(fotoAlbum);
            album2 = album;

            MusicaTask task = new MusicaTask(this);
            task.execute(String.valueOf(album.getId()));


        }
        listaMusicasRecyclerView = findViewById(R.id.lista_musica_recycler_view_albumDetalhado);
        musicas = new ArrayList<>();
        Musica musica = new Musica();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onMusicaSuccess(MusicaResponse response) {
        if(response.getData() == null){

        }
        else{
            musicas = response.getData();
            listaMusicasAdapter= new ListaMusicasDetalhadoAdapter(musicas);
            listaMusicasRecyclerView.setAdapter(listaMusicasAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            listaMusicasRecyclerView.setLayoutManager(layoutManager);
        }
    }

    @Override
    public void onMusicaError(String error) {
    }
}

