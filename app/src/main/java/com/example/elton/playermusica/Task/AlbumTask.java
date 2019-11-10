package com.example.elton.playermusica.Task;

import android.os.AsyncTask;

import com.example.elton.playermusica.Response.AlbumResponse;
import com.example.elton.playermusica.Service.SearchService;
import com.example.elton.playermusica.Util.RetrofitUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class AlbumTask extends AsyncTask<String, Void, AlbumResponse> {

    private AlbumTask.AlbumTaskDelegate delegate;

    public AlbumTask(AlbumTask.AlbumTaskDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected AlbumResponse doInBackground(String... strings) {

        SearchService service = RetrofitUtil.getInstance().createSearchService();
        Call<AlbumResponse> call = service.albumDetalhado(strings[0]);

        try {
            Response<AlbumResponse> response = call.execute();
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
    protected void onPostExecute(AlbumResponse albumResponse) {
        if (albumResponse.getData() != null) {
            delegate.onAlbumSuccess(albumResponse);
        } else {
            delegate.onAlbumError("Não foi possível realizar a busca.");
        }
    }


    public interface AlbumTaskDelegate {
        public void onAlbumSuccess(AlbumResponse response);

        public void onAlbumError(String error);
    }
}
