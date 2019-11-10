package com.example.elton.playermusica.Task;

import android.os.AsyncTask;

import com.example.elton.playermusica.Response.ArtistaResponse;
import com.example.elton.playermusica.Service.SearchService;
import com.example.elton.playermusica.Util.RetrofitUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ArtistaTask extends AsyncTask<String, Void, ArtistaResponse> {

    private ArtistaTask.ArtistaTaskDelegate delegate;

    public ArtistaTask(ArtistaTask.ArtistaTaskDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected ArtistaResponse doInBackground(String... strings) {

        SearchService service = RetrofitUtil.getInstance().createSearchService();
        Call<ArtistaResponse> call = service.artistaDetalhado(strings[0]);

        try {
            Response<ArtistaResponse> response = call.execute();
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
    protected void onPostExecute(ArtistaResponse artistaResponse) {
        if (artistaResponse.getData() != null) {
            delegate.onArtistaSuccess(artistaResponse);
        } else {
            delegate.onArtistaError("Não foi possível realizar a busca.");
        }
    }


    public interface ArtistaTaskDelegate {
        public void onArtistaSuccess(ArtistaResponse response);

        public void onArtistaError(String error);
    }
}
