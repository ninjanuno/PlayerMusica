package com.example.elton.playermusica.Task;

import android.os.AsyncTask;

import com.example.elton.playermusica.Response.MusicaResponse;
import com.example.elton.playermusica.Service.SearchService;
import com.example.elton.playermusica.Util.RetrofitUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class MusicaTask extends AsyncTask<String, Void, MusicaResponse> {

    private MusicaTask.MusicaTaskDelegate delegate;

    public MusicaTask(MusicaTask.MusicaTaskDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected MusicaResponse doInBackground(String... strings) {

        SearchService service = RetrofitUtil.getInstance().createSearchService();
        Call<MusicaResponse> call = service.musicaDetalhado(strings[0]);

        try {
            Response<MusicaResponse> response = call.execute();
            if (response.isSuccessful()) {
                // response.raw().body().string() - response em formato string
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(MusicaResponse musicaResponse) {
        if (musicaResponse.getData() != null) {
            delegate.onMusicaSuccess(musicaResponse);
        } else {
            delegate.onMusicaError("Não foi possível realizar a busca.");
        }
    }


    public interface MusicaTaskDelegate {
        public void onMusicaSuccess(MusicaResponse response);

        public void onMusicaError(String error);
    }
}
