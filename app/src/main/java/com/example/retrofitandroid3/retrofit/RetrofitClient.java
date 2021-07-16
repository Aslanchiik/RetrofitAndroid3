package com.example.retrofitandroid3.retrofit;

import com.example.retrofitandroid3.inter.CharactersApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    Retrofit retrofitClient = new Retrofit.Builder().
             // нужна ссылка для скачивания
             baseUrl("https://rickandmortyapi.com/").
                     // преобразовать в json формат
             addConverterFactory(GsonConverterFactory.create()).build();


    // метод для возвращения интерфейса
      public CharactersApiService providerCharacterApiService (){
           return retrofitClient.create(CharactersApiService.class);

     }


}
