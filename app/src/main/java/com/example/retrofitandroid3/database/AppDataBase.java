package com.example.retrofitandroid3.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.retrofitandroid3.database.dao.characterdao.CharacterDao;
import com.example.retrofitandroid3.database.dao.episodedao.EpisodeDao;
import com.example.retrofitandroid3.database.dao.locationdao.LocationsDao;
import com.example.retrofitandroid3.models.character.RickyAndMortyCharacter;
import com.example.retrofitandroid3.models.episodemodel.EpisodeRickyAndMorty;
import com.example.retrofitandroid3.models.location.LocationsRickyAndMorty;

@Database(entities = {RickyAndMortyCharacter.class, LocationsRickyAndMorty.class, EpisodeRickyAndMorty.class}, version = 3)
abstract class AppDataBase extends RoomDatabase {

    public abstract CharacterDao characterDao();

    public abstract LocationsDao locationsDao();

    public abstract EpisodeDao episodeDao();


}
