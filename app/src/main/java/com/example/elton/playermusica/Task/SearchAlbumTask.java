package com.example.elton.playermusica.Task;

import android.os.AsyncTask;

import com.example.elton.playermusica.Response.SearchAlbumResponse;
import com.example.elton.playermusica.Service.SearchService;
import com.example.elton.playermusica.Util.RetrofitUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SearchAlbumTask extends AsyncTask<String, Void, SearchAlbumResponse> {

    private SearchAlbumTask.SearchAlbumTaskDelegate delegate;

    public SearchAlbumTask(SearchAlbumTask.SearchAlbumTaskDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected SearchAlbumResponse doInBackground(String... strings) {

        SearchService service = RetrofitUtil.getInstance().createSearchService();
        Call<SearchAlbumResponse> call = service.searchAlbum(strings[0]);

        try {
            Response<SearchAlbumResponse> response = call.execute();
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
    protected void onPostExecute(SearchAlbumResponse searchAlbumResponse) {
        if (searchAlbumResponse != null) {
            delegate.onSearchAlbumSuccess(searchAlbumResponse);
        } else {
            delegate.onSearchAlbumError("Não foi possível realizar a busca.");
        }
    }


    public interface SearchAlbumTaskDelegate {
        public void onSearchAlbumSuccess(SearchAlbumResponse response);

        public void onSearchAlbumError(String error);
    }
}