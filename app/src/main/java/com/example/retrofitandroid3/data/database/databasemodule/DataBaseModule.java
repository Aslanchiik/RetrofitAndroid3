package com.example.retrofitandroid3.data.database.databasemodule;


import android.content.Context;

import com.example.retrofitandroid3.data.database.AppDataBase;
import com.example.retrofitandroid3.data.database.RoomClient;
import com.example.retrofitandroid3.data.database.dao.characterdao.CharacterDao;
import com.example.retrofitandroid3.data.database.dao.episodedao.EpisodeDao;
import com.example.retrofitandroid3.data.database.dao.locationdao.LocationsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataBaseModule {

    @Singleton
    RoomClient roomClient = new RoomClient();

    @Singleton
    @Provides
    AppDataBase provideAppDataBase(@ApplicationContext Context context) {
        return roomClient.provideRoom(context);
    }

    @Singleton
    @Provides
    CharacterDao provideCharacterDao(AppDataBase dataBase) {
        return new RoomClient().providerCharacterDao(dataBase);

    }

    @Singleton
    @Provides
    LocationsDao provideLocationDao(AppDataBase locDataBase) {
        return new RoomClient().providerLocationDao(locDataBase);
    }

    @Singleton
    @Provides
     EpisodeDao provideEpisodeDao (AppDataBase episodeBase) {
        return new RoomClient().providerEpisodeDao(episodeBase);
    }

}
