package com.example.retrofitandroid3.database.dao.episodedao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofitandroid3.models.episodemodel.EpisodeRickyAndMorty;

import java.util.List;

@Dao
public interface EpisodeDao {

    @Query("SELECT * FROM episoderickyandmorty")
    List<EpisodeRickyAndMorty> getEpisodeAll ();

     @Insert (onConflict = OnConflictStrategy.REPLACE)
       void insertAll (List<EpisodeRickyAndMorty> episodeRickyAndMorty);
}
