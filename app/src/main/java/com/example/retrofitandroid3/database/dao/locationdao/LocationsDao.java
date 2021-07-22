package com.example.retrofitandroid3.database.dao.locationdao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofitandroid3.models.location.LocationsRickyAndMorty;

import java.util.List;

@Dao
public interface LocationsDao {


    @Query("SELECT * FROM locationsrickyandmorty")
    List<LocationsRickyAndMorty> getAllLocations();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAll(List<LocationsRickyAndMorty> locationsRickyAndMorty);
}
