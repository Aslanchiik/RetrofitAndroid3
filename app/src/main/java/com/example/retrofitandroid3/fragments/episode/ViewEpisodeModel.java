package com.example.retrofitandroid3.fragments.episode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitandroid3.fragments.loacations.LocationsRickyAndMorty;
import com.example.retrofitandroid3.models.response.RickAndMortyResponse;
import com.example.retrofitandroid3.repositories.Repositories;

public class ViewEpisodeModel extends ViewModel {

    private final Repositories repository = new Repositories();

    public  int characterPage = 1;

    public MutableLiveData<RickAndMortyResponse<EpisodeRickyAndMorty>> fetchEpisode () {
        return repository.fetchEpisode(characterPage);
    }
}
