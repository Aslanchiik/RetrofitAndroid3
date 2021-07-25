package com.example.retrofitandroid3.di;

import com.example.retrofitandroid3.data.retrofit.RetrofitClient;
import com.example.retrofitandroid3.data.retrofit.apiservice.CharactersApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Singleton
    @Provides
     CharactersApiService providerApiService () {
         return  new  RetrofitClient().providerCharacterApiService();
     }

}
