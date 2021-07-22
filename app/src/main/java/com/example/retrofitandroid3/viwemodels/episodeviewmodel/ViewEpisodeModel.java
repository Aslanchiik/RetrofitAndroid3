package com.example.retrofitandroid3.viwemodels.episodeviewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitandroid3.models.response.RickAndMortyResponse;
import com.example.retrofitandroid3.repositories.Repositories;
import com.example.retrofitandroid3.models.episodemodel.EpisodeRickyAndMorty;

import java.util.ArrayList;

public class ViewEpisodeModel extends ViewModel {

    private final Repositories repository = new Repositories();

    public  int characterPage = 1;

    public MutableLiveData<RickAndMortyResponse<EpisodeRickyAndMorty>> fetchEpisode () {
        return repository.fetchEpisode(characterPage);
    }
     public ArrayList <EpisodeRickyAndMorty> getEpisodeOver () {
        return  repository.getEpisode();
     }
}
