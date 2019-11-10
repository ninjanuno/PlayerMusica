package com.example.elton.playermusica.Task;

import android.os.AsyncTask;

import com.example.elton.playermusica.Response.SearchMusicaResponse;
import com.example.elton.playermusica.Service.SearchService;
import com.example.elton.playermusica.Util.RetrofitUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SearchMusicaTask extends AsyncTask<String, Void, SearchMusicaResponse> {

    private SearchMusicaTask.SearchMusicaTaskDelegate delegate;

    public SearchMusicaTask(SearchMusicaTask.SearchMusicaTaskDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected SearchMusicaResponse doInBackground(String... strings) {

        SearchService service = RetrofitUtil.getInstance().createSearchService();
        Call<SearchMusicaResponse> call = service.searchMusica(strings[0]);

        try {
            Response<SearchMusicaResponse> response = call.execute();
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
    protected void onPostExecute(SearchMusicaResponse searchMusicaResponse) {
        if (searchMusicaResponse != null) {
            delegate.onSearchMusicaSuccess(searchMusicaResponse);
        } else {
            delegate.onSearchMusicaError("Não foi possível realizar a busca.");
        }
    }


    public interface SearchMusicaTaskDelegate {
        public void onSearchMusicaSuccess(SearchMusicaResponse response);

        public void onSearchMusicaError(String error);
    }
}
