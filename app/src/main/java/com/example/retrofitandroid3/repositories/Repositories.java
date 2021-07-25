package com.example.retrofitandroid3.repositories;


import androidx.lifecycle.MutableLiveData;

import com.example.retrofitandroid3.data.database.dao.characterdao.CharacterDao;
import com.example.retrofitandroid3.data.database.dao.episodedao.EpisodeDao;
import com.example.retrofitandroid3.data.database.dao.locationdao.LocationsDao;
import com.example.retrofitandroid3.data.retrofit.apiservice.CharactersApiService;
import com.example.retrofitandroid3.models.episodemodel.EpisodeRickyAndMorty;
import com.example.retrofitandroid3.models.location.LocationsRickyAndMorty;
import com.example.retrofitandroid3.models.character.RickyAndMortyCharacter;
import com.example.retrofitandroid3.models.response.RickAndMortyResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositories {

    CharactersApiService service;

    CharacterDao dao;

     LocationsDao locationsDao;

     EpisodeDao episodeDao;

    @Inject
    public Repositories(CharactersApiService service, CharacterDao dao, LocationsDao locationsDao, EpisodeDao episodeDao) {
        this.service = service;
        this.dao = dao;
        this.locationsDao = locationsDao;
        this.episodeDao = episodeDao;
    }


    public MutableLiveData<RickAndMortyResponse<RickyAndMortyCharacter>> fetchCharacters(int page) {

        MutableLiveData<RickAndMortyResponse<RickyAndMortyCharacter>> data = new MutableLiveData<>();

//        App.charactersApiService.
        service.fetchCharacters(page).enqueue(new Callback<RickAndMortyResponse<RickyAndMortyCharacter>>() {
            @Override
            // при успешном
            public void onResponse(Call<RickAndMortyResponse<RickyAndMortyCharacter>> call, Response<RickAndMortyResponse<RickyAndMortyCharacter>> response) {
                if (response.body() != null) {
                  dao.insertAll(response.body().getResults());
                }
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

    public ArrayList<RickyAndMortyCharacter> getCharacters() {
        return new ArrayList<>(dao.getAll());
    }

    public MutableLiveData<RickAndMortyResponse<LocationsRickyAndMorty>> fetchLocation(int page) {

        MutableLiveData<RickAndMortyResponse<LocationsRickyAndMorty>> liveData = new MutableLiveData<>();
        service.fetLocation(page).enqueue(new Callback<RickAndMortyResponse<LocationsRickyAndMorty>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<LocationsRickyAndMorty>> call, Response<RickAndMortyResponse<LocationsRickyAndMorty>> response) {
                if (response.body() != null) {
                     locationsDao.addAll(response.body().getResults());
                }
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<LocationsRickyAndMorty>> call, Throwable t) {
                liveData.setValue(null);
            }
        });
        return liveData;
    }

    public ArrayList<LocationsRickyAndMorty> getLocations() {
        return new ArrayList<>(locationsDao.getAllLocations());
    }


    public MutableLiveData<RickAndMortyResponse<EpisodeRickyAndMorty>> fetchEpisode(int page) {

        MutableLiveData<RickAndMortyResponse<EpisodeRickyAndMorty>> mutableLiveData = new MutableLiveData<>();

        service.fetEpisode(page).enqueue(new Callback<RickAndMortyResponse<EpisodeRickyAndMorty>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<EpisodeRickyAndMorty>> call, Response<RickAndMortyResponse<EpisodeRickyAndMorty>> response) {
                if (response.body() != null) {
                    episodeDao.insertAll(response.body().getResults());
                    mutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<EpisodeRickyAndMorty>> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }

    public ArrayList<EpisodeRickyAndMorty> getEpisode() {
        return new ArrayList<>(episodeDao.getEpisodeAll());
    }
}
