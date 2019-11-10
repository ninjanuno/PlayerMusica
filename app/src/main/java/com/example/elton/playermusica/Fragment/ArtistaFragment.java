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

import com.example.elton.playermusica.Adapter.ListaArtistasAdapter;
import com.example.elton.playermusica.Model.Artista;
import com.example.elton.playermusica.R;
import com.example.elton.playermusica.Response.SearchArtistaResponse;
import com.example.elton.playermusica.Task.SearchArtistTask;

import java.util.ArrayList;
import java.util.List;

public class ArtistaFragment extends Fragment implements View.OnClickListener, SearchArtistTask.SearchArtistTaskDelegate {

    View view;
    private ImageView setaArtista;
    private EditText artistaText;
    private List<Artista> artistas;
    private RecyclerView listaArtistasRecyclerView;
    private ListaArtistasAdapter listaArtistasAdapter;
    private ProgressDialog progressDialog;

    public ArtistaFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.artista_fragment,container,false);

        artistaText = view.findViewById(R.id.artista_text);
        setaArtista = view.findViewById(R.id.artista_seta);
        setaArtista.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        listaArtistasRecyclerView = view.findViewById(R.id.lista_artista_recycler_view);
        artistas = new ArrayList<>();

        SearchArtistTask task = new SearchArtistTask(this);
        progressDialog = ProgressDialog.show(getContext(), "Aguarde", "Buscando Artistas", true, false);
        task.execute(artistaText.getText().toString());



    }

    @Override
    public void onSearchArtistSuccess(SearchArtistaResponse response) {
        progressDialog.dismiss();
        progressDialog = null;
        if(response.getData() == null){

        }
        else{
            artistas = response.getData();
            listaArtistasAdapter= new ListaArtistasAdapter(artistas);
            listaArtistasRecyclerView.setAdapter(listaArtistasAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            listaArtistasRecyclerView.setLayoutManager(layoutManager);
        }
    }

    @Override
    public void onSearchArtistError(String error) {
        progressDialog.dismiss();
        progressDialog = null;
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();

    }
}
