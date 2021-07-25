package com.example.retrofitandroid3.viwemodels.loctaionviewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitandroid3.data.retrofit.apiservice.CharactersApiService;
import com.example.retrofitandroid3.models.response.RickAndMortyResponse;
import com.example.retrofitandroid3.repositories.Repositories;
import com.example.retrofitandroid3.models.location.LocationsRickyAndMorty;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.InstallIn;
import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class ViewModelLocations extends ViewModel {

    Repositories repository;

    @Inject
    public ViewModelLocations(Repositories repository) {
        this.repository = repository;
    }

    public int characterPage = 1;

    public MutableLiveData<RickAndMortyResponse<LocationsRickyAndMorty>> fetchLocation() {
        return repository.fetchLocation(characterPage);
    }

    public ArrayList<LocationsRickyAndMorty> getLocationsOver() {
        return repository.getLocations();
    }
}
