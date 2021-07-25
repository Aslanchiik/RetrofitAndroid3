package com.example.retrofitandroid3.data.retrofit;

import com.example.retrofitandroid3.data.retrofit.apiservice.CharactersApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

     private final OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(providerInspector()).
             connectTimeout(30, TimeUnit.SECONDS).
             readTimeout(30, TimeUnit.SECONDS).
             writeTimeout(30, TimeUnit.SECONDS).build();


     // логирование
      private HttpLoggingInterceptor providerInspector () {
          return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }


    Retrofit retrofitClient = new Retrofit.Builder().
             // нужна ссылка для скачивания
             baseUrl("https://rickandmortyapi.com/").
                    // хтпп клиент
                    client(okHttpClient).
                     // преобразовать в json формат
             addConverterFactory(GsonConverterFactory.create()).build();


    // метод для возвращения интерфейса
      public CharactersApiService providerCharacterApiService (){
           return retrofitClient.create(CharactersApiService.class);

     }


}
