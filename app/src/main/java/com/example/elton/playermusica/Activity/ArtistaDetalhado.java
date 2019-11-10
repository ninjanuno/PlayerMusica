package com.example.elton.playermusica.Activity;

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
import com.example.elton.playermusica.Adapter.ListaArtistasDetalhadoAdapter;
import com.example.elton.playermusica.Model.AlbumAmpliado;
import com.example.elton.playermusica.Model.Artista;
import com.example.elton.playermusica.Model.ArtistaAmpliado;
import com.example.elton.playermusica.R;
import com.example.elton.playermusica.Response.AlbumResponse;
import com.example.elton.playermusica.Response.ArtistaResponse;
import com.example.elton.playermusica.Task.AlbumTask;
import com.example.elton.playermusica.Task.ArtistaTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArtistaDetalhado extends AppCompatActivity implements View.OnClickListener, AlbumTask.AlbumTaskDelegate, ArtistaTask.ArtistaTaskDelegate {

    public static final String PARAM_OBJETO = "PARAM_OBJETO";
    private List<AlbumAmpliado> albuns;
    private List<ArtistaAmpliado> artistas;
    private RecyclerView listaAlbunsRecyclerView;
    private ListaAlbumsDetalhadoAdapter listaAlbunsAdapter;
    private ListaArtistasDetalhadoAdapter listaArtistaAdapter;
    private TextView nomeArtista,numeroAlbuns, numerofas;
    private ImageView fotoArtista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artista_detalhado);
        nomeArtista = findViewById(R.id.artistaDetalhado_nome);
        fotoArtista = findViewById(R.id.artistaDetalhado_foto_artista);
        numeroAlbuns = findViewById(R.id.artistaDetalhado_albuns_numero);
        numerofas = findViewById(R.id.artistaDetalhado_fas_numero);
        //----------------------pega o put extra------------------------------------------------
        Intent intent = getIntent();

        listaAlbunsRecyclerView = findViewById(R.id.lista_album_recycler_view_artistaDetalhado);
        albuns = new ArrayList<>();
        artistas = new ArrayList<>();

        if(intent.getSerializableExtra(PARAM_OBJETO) instanceof Artista){
            Artista artista = (Artista) intent.getSerializableExtra(PARAM_OBJETO);
            nomeArtista.setText(artista.getName());
            numeroAlbuns.setText(String.valueOf(artista.getNumeroAlbuns()));
            numerofas.setText(String.valueOf(artista.getNumeroFas()));
            Picasso.with(this).load(artista.getPicture()).into(fotoArtista);


            AlbumTask task = new AlbumTask(this);
            task.execute(String.valueOf(artista.getId()));

            ArtistaTask task2 = new ArtistaTask(this);
            task2.execute(String.valueOf(artista.getId()));
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onAlbumSuccess(AlbumResponse response) {
        if(response.getData() == null){
            Toast.makeText(this, "Sem resultados", Toast.LENGTH_LONG).show();
        }
        else{
            albuns = response.getData();

            listaAlbunsAdapter= new ListaAlbumsDetalhadoAdapter(albuns);
            listaAlbunsRecyclerView.setAdapter(listaAlbunsAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            listaAlbunsRecyclerView.setLayoutManager(layoutManager);
        }
    }

    @Override
    public void onAlbumError(String error) {

    }

    @Override
    public void onArtistaSuccess(ArtistaResponse response) {
        if(response.getData() == null){
            Toast.makeText(this, "Sem resultados", Toast.LENGTH_LONG).show();
        }
        else{

        }
    }

    @Override
    public void onArtistaError(String error) {
    }
}
