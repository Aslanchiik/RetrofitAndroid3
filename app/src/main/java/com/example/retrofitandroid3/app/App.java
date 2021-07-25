package com.example.retrofitandroid3.app;

import android.app.Application;
import dagger.hilt.android.HiltAndroidApp;


@HiltAndroidApp
public class App extends Application {

  //  public static CharactersApiService charactersApiService;

//    public static CharacterDao characterDao;
//
//    public static LocationsDao locationsDao;
//
//    public static EpisodeDao episodeDao;

    @Override
    public void onCreate() {
        super.onCreate();
     //   charactersApiService = new RetrofitClient().providerCharacterApiService();
     //   RoomClient roomClient = new RoomClient();
//        characterDao = roomClient.providerCharacterDao(roomClient.provideRoom(this));
//        locationsDao = roomClient.providerLocationDao(roomClient.provideRoom(this));
//        episodeDao = roomClient.providerEpisodeDao(roomClient.provideRoom(this));
    }
}
