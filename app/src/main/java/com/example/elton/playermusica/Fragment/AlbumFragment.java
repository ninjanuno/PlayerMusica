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

import com.example.elton.playermusica.Adapter.ListaAlbunsAdapter;
import com.example.elton.playermusica.Model.Album;
import com.example.elton.playermusica.R;
import com.example.elton.playermusica.Response.SearchAlbumResponse;
import com.example.elton.playermusica.Task.SearchAlbumTask;
import com.example.elton.playermusica.Task.SearchArtistTask;

import java.util.ArrayList;
import java.util.List;

public class AlbumFragment extends Fragment implements View.OnClickListener, SearchAlbumTask.SearchAlbumTaskDelegate {
    View view;

    private ImageView setaAlbum;
    private EditText albumText;
    private List<Album> albuns;
    private RecyclerView listaAlbunsRecyclerView;
    private ListaAlbunsAdapter listaAlbunsAdapter;
    private ProgressDialog progressDialog;

    public AlbumFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.album_fragment,container,false);

        albumText = view.findViewById(R.id.album_text);
        setaAlbum = view.findViewById(R.id.album_seta);
        setaAlbum.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        listaAlbunsRecyclerView = view.findViewById(R.id.lista_album_recycler_view);
        albuns = new ArrayList<>();

        SearchAlbumTask task = new SearchAlbumTask(this);
        progressDialog = ProgressDialog.show(getContext(), "Aguarde", "Buscando Albuns", true, false);
        task.execute(albumText.getText().toString());


    }


    @Override
    public void onSearchAlbumSuccess(SearchAlbumResponse response) {
        progressDialog.dismiss();
        progressDialog = null;
        if(response.getData() == null){

        }
        else{
            albuns = response.getData();
            listaAlbunsAdapter= new ListaAlbunsAdapter(albuns);
            listaAlbunsRecyclerView.setAdapter(listaAlbunsAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            listaAlbunsRecyclerView.setLayoutManager(layoutManager);
        }

    }

    @Override
    public void onSearchAlbumError(String error) {
        progressDialog.dismiss();
        progressDialog = null;
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}
