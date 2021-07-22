package com.example.retrofitandroid3.database.dao.characterdao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofitandroid3.models.character.RickyAndMortyCharacter;

import java.util.List;

@Dao
public interface CharacterDao {

  @Query("SELECT * FROM rickyandmortycharacter")
    List<RickyAndMortyCharacter> getAll();

  @Insert (onConflict = OnConflictStrategy.REPLACE)
     void insertAll (List<RickyAndMortyCharacter> rickyAndMortyCharacters);


}
