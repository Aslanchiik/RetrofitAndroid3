package com.example.retrofitandroid3.app;

import android.app.Application;

import com.example.retrofitandroid3.inter.CharactersApiService;
import com.example.retrofitandroid3.retrofit.RetrofitClient;

import retrofit2.Retrofit;

public class App extends Application {

    public static CharactersApiService charactersApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        charactersApiService = new RetrofitClient().providerCharacterApiService();
    }
}
