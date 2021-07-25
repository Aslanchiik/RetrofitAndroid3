package com.example.retrofitandroid3.viwemodels.charactervieewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitandroid3.models.character.RickyAndMortyCharacter;
import com.example.retrofitandroid3.models.response.RickAndMortyResponse;
import com.example.retrofitandroid3.repositories.Repositories;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CharacterViewModel extends ViewModel {

   Repositories repository;

   @Inject
    public CharacterViewModel(Repositories repository) {
        this.repository = repository;
    }

    public int characterPage = 1;

    public MutableLiveData<RickAndMortyResponse<RickyAndMortyCharacter>> fetchCharacters() {
        return repository.fetchCharacters(characterPage);
    }

    public ArrayList<RickyAndMortyCharacter> getCharacters() {
        return repository.getCharacters();
    }
}
