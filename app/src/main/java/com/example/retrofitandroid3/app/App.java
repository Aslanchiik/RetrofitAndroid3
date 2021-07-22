package com.example.retrofitandroid3.app;

import android.app.Application;

import com.example.retrofitandroid3.data.retrofit.apiservice.CharactersApiService;
import com.example.retrofitandroid3.database.RoomClient;
import com.example.retrofitandroid3.database.dao.characterdao.CharacterDao;
import com.example.retrofitandroid3.database.dao.episodedao.EpisodeDao;
import com.example.retrofitandroid3.database.dao.locationdao.LocationsDao;
import com.example.retrofitandroid3.data.retrofit.RetrofitClient;

public class App extends Application {

    public static CharactersApiService charactersApiService;
    public static CharacterDao characterDao;
    public static LocationsDao locationsDao;
    public static EpisodeDao episodeDao;

    @Override
    public void onCreate() {
        super.onCreate();
        charactersApiService = new RetrofitClient().providerCharacterApiService();
        RoomClient roomClient = new RoomClient();
        characterDao = roomClient.providerCharacterDao(roomClient.provideRoom(this));
        locationsDao = roomClient.providerLocationDao(roomClient.provideRoom(this));
        episodeDao = roomClient.providerEpisodeDao(roomClient.provideRoom(this));
    }
}
