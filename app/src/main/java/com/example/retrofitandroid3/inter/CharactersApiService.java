package com.example.retrofitandroid3.inter;

import com.example.retrofitandroid3.character.RickyAndMortyCharacter;
import com.example.retrofitandroid3.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharactersApiService {

    @GET ("api/character")
    Call<RickAndMortyResponse<RickyAndMortyCharacter>> fetchCharacters ();

     @GET ("api/character/{id}")
      Call<RickyAndMortyCharacter> fetchCharacter (@Path("id") int id);
}
