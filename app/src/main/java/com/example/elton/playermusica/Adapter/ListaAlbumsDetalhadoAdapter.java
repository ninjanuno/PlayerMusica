package com.example.elton.playermusica.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elton.playermusica.Activity.AlbumDetalhado;
import com.example.elton.playermusica.Model.AlbumAmpliado;
import com.example.elton.playermusica.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListaAlbumsDetalhadoAdapter extends RecyclerView.Adapter<ListaAlbumsDetalhadoAdapter.ItemViewHolder> {
    private static final int ADAPTER_ARTISTA_REQUEST_CODE = 1;
    private List<AlbumAmpliado> albums;

    public ListaAlbumsDetalhadoAdapter(List<AlbumAmpliado> albums) {
        this.albums = albums;
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    @Override
    public ListaAlbumsDetalhadoAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_album, parent, false);
        ListaAlbumsDetalhadoAdapter.ItemViewHolder viewHolder = new ListaAlbumsDetalhadoAdapter.ItemViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListaAlbumsDetalhadoAdapter.ItemViewHolder holder, int position) {
        final AlbumAmpliado album = albums.get(position);
        holder.preencher(album);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AlbumDetalhado.class);
                intent.putExtra(AlbumDetalhado.PARAM_OBJETO, album);//passa o album para os cara
                ((Activity) v.getContext()).startActivityForResult(intent, ADAPTER_ARTISTA_REQUEST_CODE);//gambiara precisa passar a activity ((Activity) v.getContext())
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView nomeAlbumTextView, nomeAlbumArtistaTextView;
        private ImageView imagemAlbum;

        public ItemViewHolder(View itemView) {
            super(itemView);
            nomeAlbumTextView = itemView.findViewById(R.id.album_item_nome);
            nomeAlbumArtistaTextView = itemView.findViewById(R.id.album_item_nomeArtista);
            imagemAlbum = itemView.findViewById(R.id.album_item_imagem);
        }

        public void preencher(AlbumAmpliado album) {
            nomeAlbumTextView.setText(album.getTitle());
            nomeAlbumArtistaTextView.setText("");
            Picasso.with(itemView.getContext()).load(album.getCover()).into(imagemAlbum);
        }
    }
}