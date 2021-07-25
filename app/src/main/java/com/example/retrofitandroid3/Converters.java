package com.example.retrofitandroid3;
import android.webkit.WebStorage;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.retrofitandroid3.models.character.RickyAndMortyCharacter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {

     @TypeConverter
      public Origin fromOrigin (String value){
         return new Gson().fromJson(value, new TypeToken<Origin>(){}.getType());
     }


     @TypeConverter
     public String toJson (Origin value){
         return new Gson().toJson(value,new TypeToken<Origin>(){}.getType());
     }
}
