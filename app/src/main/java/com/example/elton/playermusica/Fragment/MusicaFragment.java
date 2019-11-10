package com.example.elton.playermusica.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.elton.playermusica.Adapter.ListaMusicasAdapter;
import com.example.elton.playermusica.Model.Musica;
import com.example.elton.playermusica.R;
import com.example.elton.playermusica.Response.SearchMusicaResponse;
import com.example.elton.playermusica.Task.SearchAlbumTask;
import com.example.elton.playermusica.Task.SearchMusicaTask;

import java.util.ArrayList;
import java.util.List;

public class MusicaFragment extends Fragment implements View.OnClickListener, SearchMusicaTask.SearchMusicaTaskDelegate {
    View view;

    private ImageView setaMusica;
    private EditText musicaText;
    private List<Musica> musicas;
    private RecyclerView listaMusiRecyclerView;
    private ListaMusicasAdapter listaMusicasAdapter;
    private ProgressDialog progressDialog;


    public MusicaFragment() {

    }
    //getactivity
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.musica_fragment,container,false);

        musicaText = view.findViewById(R.id.musica_text);
        setaMusica = view.findViewById(R.id.musica_seta);
        setaMusica.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        listaMusiRecyclerView = view.findViewById(R.id.lista_musica_recycler_view);
        musicas = new ArrayList<>();

        SearchMusicaTask task = new SearchMusicaTask(this);
        progressDialog = ProgressDialog.show(getContext(), "Aguarde", "Buscando Musicas", true, false);
        task.execute(musicaText.getText().toString());


    }


    @Override
    public void onSearchMusicaSuccess(SearchMusicaResponse response) {
        progressDialog.dismiss();
        progressDialog = null;
        if(response.getData() == null){

        }
        else{
            musicas = response.getData();
            listaMusicasAdapter= new ListaMusicasAdapter(musicas);
            listaMusiRecyclerView.setAdapter(listaMusicasAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            listaMusiRecyclerView.setLayoutManager(layoutManager);
        }
    }

    @Override
    public void onSearchMusicaError(String error) {
        progressDialog.dismiss();
        progressDialog = null;
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}