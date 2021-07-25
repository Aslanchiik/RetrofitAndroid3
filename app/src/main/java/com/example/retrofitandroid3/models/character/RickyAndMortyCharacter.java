package com.example.retrofitandroid3.models.character;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.retrofitandroid3.Origin;
import com.google.gson.annotations.SerializedName;

@Entity
public class RickyAndMortyCharacter {

    @PrimaryKey
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("status")
    public String status;

     @SerializedName("image")
     public String image;

    @SerializedName("origin")
    public Origin origin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


