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

import com.example.elton.playermusica.Activity.MusicaDetalhado;
import com.example.elton.playermusica.Model.MusicaAmpliado;
import com.example.elton.playermusica.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListaMusicasDetalhadoAdapter extends RecyclerView.Adapter<ListaMusicasDetalhadoAdapter.ItemViewHolder>{
    private static final int ADAPTER_ARTISTA_REQUEST_CODE=1;
    private List<MusicaAmpliado> musicas;

    public ListaMusicasDetalhadoAdapter(List<MusicaAmpliado> musicas){
        this.musicas = musicas;
    }
    @Override
    public int getItemCount() {
        return musicas.size();
    }
    @Override
    public ListaMusicasDetalhadoAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_musica, parent,false);
        ListaMusicasDetalhadoAdapter.ItemViewHolder viewHolder = new ListaMusicasDetalhadoAdapter.ItemViewHolder(itemView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ListaMusicasDetalhadoAdapter.ItemViewHolder holder, int position) {
        final MusicaAmpliado musica = musicas.get(position);
        holder.preencher(musica);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MusicaDetalhado.class);
                intent.putExtra(MusicaDetalhado.PARAM_OBJETO,musica);//passa o musica para os cara
                ((Activity) v.getContext()).startActivityForResult(intent,ADAPTER_ARTISTA_REQUEST_CODE);//gambiara precisa passar a activity ((Activity) v.getContext())
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView nomeMusicaTextView,duracaoMusicaTextView;
        private ImageView imagemMusica;

        public ItemViewHolder(View itemView) {
            super(itemView);
            nomeMusicaTextView = itemView.findViewById(R.id.musica_item_nome);
            duracaoMusicaTextView = itemView.findViewById(R.id.musica_item_duracao);
            imagemMusica = itemView.findViewById(R.id.musica_item_imagem);
        }

        public void preencher(MusicaAmpliado musica) {
            nomeMusicaTextView.setText(musica.getTitle());
            duracaoMusicaTextView.setText(String.valueOf(timeConversion(musica.getDuration())));
            //Picasso.with(itemView.getContext()).load(musica.getAlbum().getCover()).into(imagemMusica);

        }
    }
    private static String timeConversion(int totalSeconds) {

        final int MINUTES_IN_AN_HOUR = 60;
        final int SECONDS_IN_A_MINUTE = 60;

        int seconds = totalSeconds % SECONDS_IN_A_MINUTE;
        int totalMinutes = totalSeconds / SECONDS_IN_A_MINUTE;
        int minutes = totalMinutes % MINUTES_IN_AN_HOUR;
        int hours = totalMinutes / MINUTES_IN_AN_HOUR;


        if (seconds < 10) {
            if(seconds < 6 && seconds !=0){
                seconds = seconds * 10;
            }
            else{
                if (hours == 0) {
                    return minutes + ":0" + seconds;
                } else {
                    return hours + ":" + minutes + ":0" + seconds;

                }
            }
        }

        if (hours == 0) {
            return minutes + ":" + seconds;
        } else {
            return hours + ":" + minutes + ":" + seconds;

        }
    }
}
