package com.example.elton.playermusica.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.deezer.sdk.model.Permissions;
import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.connect.SessionStore;
import com.deezer.sdk.network.connect.event.DialogListener;
import com.deezer.sdk.network.request.event.DeezerError;
import com.deezer.sdk.player.TrackPlayer;
import com.deezer.sdk.player.exception.TooManyPlayersExceptions;
import com.deezer.sdk.player.networkcheck.WifiAndMobileNetworkStateChecker;
import com.example.elton.playermusica.Model.Musica;
import com.example.elton.playermusica.R;

public class MusicaDetalhado extends AppCompatActivity implements View.OnClickListener, DialogListener {

    public static final String PARAM_OBJETO = "PARAM_OBJETO";
    private TextView nomeMusica,tempoMusica;
    private ImageView imagemMusica,playMusica;
    private DeezerConnect deezerConnect;
    private SessionStore sessionStore;
    private TrackPlayer trackPlayer;
    private boolean play = true;
    private Musica musica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica_detalhado);
        nomeMusica = findViewById(R.id.musicaDetalhado_nome_musica);
        tempoMusica = findViewById(R.id.musicaDetalhado_tempo_texto);
        imagemMusica = findViewById(R.id.mmusicaDetalhado_foto_musica);
        playMusica = findViewById(R.id.musicaDetalhado_play_imagem);
        Intent intent = getIntent();
        if(intent.getSerializableExtra(PARAM_OBJETO) instanceof Musica){
            musica = (Musica) intent.getSerializableExtra(PARAM_OBJETO);
            nomeMusica.setText(musica.getTitle());
            tempoMusica.setText(String.valueOf(timeConversion(musica.getDuration())));
            //Picasso.with(this).load(musica.getAlbum().getCover()).into(imagemMusica);
        }
        playMusica.setOnClickListener(this);


        deezerConnect = new DeezerConnect(this,"313044");//numero do site do deezer
        sessionStore = new SessionStore();
        if(!sessionStore.restore(deezerConnect,this)){
            //Usuario nao logou no deezer pelo APP
            //Solicitar que o usuario logue

            String[] persmissoes = new String[]{
                    Permissions.BASIC_ACCESS,
                    Permissions.MANAGE_LIBRARY,
                    Permissions.LISTENING_HISTORY
            };

            deezerConnect.authorize(this,persmissoes,this);//talvez de ruim cuidar a activity

        }
        else {
            //usuario Logado
            creatTrackPlayer();
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

    @Override
    public void onClick(View view) {
        if(play == true){
            playMusica.setBackgroundResource(R.drawable.baseline_pause_black_36);
            play = false;

            long idMusica = 0;
            try{
                idMusica = musica.getId();
            }
            catch (NumberFormatException e){
                Toast.makeText(this, "Id da musica nao encontrado", Toast.LENGTH_LONG).show();
                return;
            }


            if(trackPlayer == null){
                Toast.makeText(this, "Player nao Disponivel", Toast.LENGTH_LONG).show();
                return;
            }

            trackPlayer.playTrack(idMusica);



        }
        else{
            playMusica.setBackgroundResource(R.drawable.baseline_play_arrow_black_36);
            play = true;
            trackPlayer.pause();
        }
    }

    @Override
    public void onComplete(Bundle bundle) {
        sessionStore.save(deezerConnect,this);//salva a sessao do deezer do usuario
        creatTrackPlayer();
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onException(Exception e) {

    }

    private  void creatTrackPlayer(){
        try {
            trackPlayer = new TrackPlayer(getApplication(),deezerConnect, new WifiAndMobileNetworkStateChecker());//ultimo cara se ter wifi ou 3g
        } catch (TooManyPlayersExceptions tooManyPlayersExceptions) {
            tooManyPlayersExceptions.printStackTrace();
        } catch (DeezerError deezerError) {
            deezerError.printStackTrace();
        }
    }
}
