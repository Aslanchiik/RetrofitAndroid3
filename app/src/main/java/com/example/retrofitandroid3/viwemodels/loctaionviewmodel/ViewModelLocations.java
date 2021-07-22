package com.example.retrofitandroid3.viwemodels.loctaionviewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitandroid3.models.response.RickAndMortyResponse;
import com.example.retrofitandroid3.repositories.Repositories;
import com.example.retrofitandroid3.models.location.LocationsRickyAndMorty;

import java.util.ArrayList;

public class ViewModelLocations extends ViewModel {

    private final Repositories repository = new Repositories();

    public  int characterPage = 1;

    public MutableLiveData<RickAndMortyResponse<LocationsRickyAndMorty>> fetchLocation () {
        return repository.fetchLocation(characterPage);
    }
      public ArrayList <LocationsRickyAndMorty> getLocationsOver () {
        return repository.getLocations();
      }
}
