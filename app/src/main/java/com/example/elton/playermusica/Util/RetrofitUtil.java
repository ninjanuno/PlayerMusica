package com.example.elton.playermusica.Util;

import com.example.elton.playermusica.Service.SearchService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitUtil{

    private static RetrofitUtil instance;
    private Retrofit retrofit;

    private RetrofitUtil(){

        ObjectMapper jackasonMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com")
                .addConverterFactory(JacksonConverterFactory.create(jackasonMapper))
                .build();

    }

    public static RetrofitUtil getInstance(){
        if(instance == null){
            instance = new RetrofitUtil();
        }
        return instance;
    }

    public SearchService createSearchService(){
        return retrofit.create(SearchService.class);
    }
}
