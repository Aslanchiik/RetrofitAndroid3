package com.example.retrofitandroid3.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofitandroid3.app.App;
import com.example.retrofitandroid3.fragments.episode.EpisodeRickyAndMorty;
import com.example.retrofitandroid3.fragments.loacations.LocationsRickyAndMorty;
import com.example.retrofitandroid3.models.character.RickyAndMortyCharacter;
import com.example.retrofitandroid3.models.response.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositories {

    public MutableLiveData<RickAndMortyResponse<RickyAndMortyCharacter>> fetchCharacters(int page) {

        MutableLiveData<RickAndMortyResponse<RickyAndMortyCharacter>> data = new MutableLiveData<>();

        App.charactersApiService.fetchCharacters(page).enqueue(new Callback<RickAndMortyResponse<RickyAndMortyCharacter>>() {
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
        return data;
    }

    public MutableLiveData<RickAndMortyResponse<LocationsRickyAndMorty>> fetchLocation(int page) {

        MutableLiveData<RickAndMortyResponse<LocationsRickyAndMorty>> liveData = new MutableLiveData<>();

        App.charactersApiService.fetLocation(page).enqueue(new Callback<RickAndMortyResponse<LocationsRickyAndMorty>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<LocationsRickyAndMorty>> call, Response<RickAndMortyResponse<LocationsRickyAndMorty>> response) {
                liveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<LocationsRickyAndMorty>> call, Throwable t) {
                       liveData.setValue(null);
            }
        });
        return liveData;
    }

      public MutableLiveData <RickAndMortyResponse<EpisodeRickyAndMorty>> fetchEpisode (int page) {

         MutableLiveData <RickAndMortyResponse<EpisodeRickyAndMorty>> mutableLiveData = new MutableLiveData<>();

           App.charactersApiService.fetEpisode(page).enqueue(new Callback<RickAndMortyResponse<EpisodeRickyAndMorty>>() {
               @Override
               public void onResponse(Call<RickAndMortyResponse<EpisodeRickyAndMorty>> call, Response<RickAndMortyResponse<EpisodeRickyAndMorty>> response) {
                   mutableLiveData.setValue(response.body());
               }

               @Override
               public void onFailure(Call<RickAndMortyResponse<EpisodeRickyAndMorty>> call, Throwable t) {
                       mutableLiveData.setValue(null);
               }
           });
           return  mutableLiveData;
      }
}
