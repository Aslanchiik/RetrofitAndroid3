package com.example.retrofitandroid3.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitandroid3.app.App;
import com.example.retrofitandroid3.character.RickyAndMortyCharacter;
import com.example.retrofitandroid3.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends ViewModel {

     public MutableLiveData <RickAndMortyResponse<RickyAndMortyCharacter>> data = new MutableLiveData<>();

     public  void fetchCharacters (){
         App.charactersApiService.fetchCharacters().enqueue(new Callback<RickAndMortyResponse<RickyAndMortyCharacter>>() {
             @Override

              // при успешном
             public void onResponse(Call<RickAndMortyResponse<RickyAndMortyCharacter>> call, Response<RickAndMortyResponse<RickyAndMortyCharacter>> response) {
                 data.setValue(response.body());
             }
                 // при не успешном
             @Override
             public void onFailure(Call<RickAndMortyResponse<RickyAndMortyCharacter>> call, Throwable t) {
              data.setValue(null);
             }
         });
     }
}
