package com.example.elton.playermusica.Task;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.elton.playermusica.Response.SearchArtistaResponse;
import com.example.elton.playermusica.Service.SearchService;
import com.example.elton.playermusica.Util.RetrofitUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SearchArtistTask extends AsyncTask<String, Void, SearchArtistaResponse> {

    private SearchArtistTaskDelegate delegate;

    public SearchArtistTask(SearchArtistTaskDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected SearchArtistaResponse doInBackground(String... strings) {

        SearchService service = RetrofitUtil.getInstance().createSearchService();
        Call<SearchArtistaResponse> call = service.searchArtista(strings[0]);

        try {
            Response<SearchArtistaResponse> response = call.execute();
            if(response.isSuccessful()){
                // response.raw().body().string() - response em formato string
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(SearchArtistaResponse searchArtistaResponse) {
        if(searchArtistaResponse != null){
            delegate.onSearchArtistSuccess(searchArtistaResponse);
        } else {
            delegate.onSearchArtistError("Não foi possível realizar a busca.");
        }
    }


    public interface SearchArtistTaskDelegate {
        public void onSearchArtistSuccess(SearchArtistaResponse response);
        public void onSearchArtistError(String error);
    }
}
