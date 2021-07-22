package com.example.retrofitandroid3.database;

import android.content.Context;

import androidx.room.Room;

import com.example.retrofitandroid3.database.dao.characterdao.CharacterDao;
import com.example.retrofitandroid3.database.dao.episodedao.EpisodeDao;
import com.example.retrofitandroid3.database.dao.locationdao.LocationsDao;

public class RoomClient {

    public AppDataBase provideRoom (Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, "rickyAndMorty").allowMainThreadQueries().fallbackToDestructiveMigration().build();

    }
     public CharacterDao providerCharacterDao (AppDataBase appDataBase) {
        return appDataBase.characterDao();
     }

      public LocationsDao providerLocationDao (AppDataBase dataBase) {
         return dataBase.locationsDao();
      }

        public EpisodeDao providerEpisodeDao (AppDataBase episodeBase) {
            return episodeBase.episodeDao();
        }
}
