package com.example.retrofitandroid3.inter;

import com.example.retrofitandroid3.fragments.episode.EpisodeRickyAndMorty;
import com.example.retrofitandroid3.fragments.loacations.LocationsRickyAndMorty;
import com.example.retrofitandroid3.models.character.RickyAndMortyCharacter;
import com.example.retrofitandroid3.models.response.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharactersApiService {

    @GET("api/character")
    Call<RickAndMortyResponse<RickyAndMortyCharacter>> fetchCharacters(@Query("page") int page);


    @GET("api/character/{id}")
    Call<RickyAndMortyCharacter> fetchCharacter(@Path("id") int id);


    @GET("api/location")
    Call<RickAndMortyResponse<LocationsRickyAndMorty>> fetLocation(@Query("page") int page);

    @GET("api/episode")
    Call<RickAndMortyResponse<EpisodeRickyAndMorty>> fetEpisode(@Query("page") int page);
}
