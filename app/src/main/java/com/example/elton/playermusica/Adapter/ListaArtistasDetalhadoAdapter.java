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

import com.example.elton.playermusica.Activity.ArtistaDetalhado;
import com.example.elton.playermusica.Model.ArtistaAmpliado;
import com.example.elton.playermusica.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListaArtistasDetalhadoAdapter extends RecyclerView.Adapter<ListaArtistasDetalhadoAdapter.ItemViewHolder>{
    private static final int ADAPTER_ARTISTA_REQUEST_CODE=1;
    private List<ArtistaAmpliado> artistas;
    private ListaArtistasDetalhadoAdapter.ItemViewHolder listener;

    public ListaArtistasDetalhadoAdapter(List<ArtistaAmpliado> artistas){
        this.artistas = artistas;
        this.listener = listener;
    }
    @Override
    public int getItemCount() {
        return artistas.size();
    }
    @Override
    public  ListaArtistasDetalhadoAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_artista, parent,false);
        ListaArtistasDetalhadoAdapter.ItemViewHolder viewHolder = new ListaArtistasDetalhadoAdapter.ItemViewHolder(itemView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ListaArtistasDetalhadoAdapter.ItemViewHolder holder, int position) {

        final ArtistaAmpliado artista = artistas.get(position);//tem que ser final
        holder.preencher(artista);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ArtistaDetalhado.class);
                intent.putExtra(ArtistaDetalhado.PARAM_OBJETO,artista);//passa o artista para os cara
                ((Activity) v.getContext()).startActivityForResult(intent,ADAPTER_ARTISTA_REQUEST_CODE);//gambiara precisa passar a activity ((Activity) v.getContext())
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ListaArtistasDetalhadoAdapter.ArtistaAdapterListener holderListener;
        private TextView nomeArtistaTextView;
        private ImageView imagemArtista;

        public ItemViewHolder(View itemView) {
            super(itemView);
            nomeArtistaTextView = itemView.findViewById(R.id.artista_item_nome);
            imagemArtista = itemView.findViewById(R.id.artista_item_imagem);
        }
        public void preencher(ArtistaAmpliado artista) {
            nomeArtistaTextView.setText(artista.getName());
            Picasso.with(itemView.getContext()).load(artista.getPicture()).into(imagemArtista);

        }

        @Override
        public void onClick(View v) {

        }
    }
    public interface ArtistaAdapterListener {
        public void onSearchItemClick(int position);
    }

}