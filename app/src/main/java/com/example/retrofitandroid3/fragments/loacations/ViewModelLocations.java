package com.example.retrofitandroid3.fragments.loacations;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitandroid3.models.character.RickyAndMortyCharacter;
import com.example.retrofitandroid3.models.response.RickAndMortyResponse;
import com.example.retrofitandroid3.repositories.Repositories;

public class ViewModelLocations extends ViewModel {

    private final Repositories repository = new Repositories();

    public  int characterPage = 1;

    public MutableLiveData<RickAndMortyResponse<LocationsRickyAndMorty>> fetchLocation () {
        return repository.fetchLocation(characterPage);
    }
}
