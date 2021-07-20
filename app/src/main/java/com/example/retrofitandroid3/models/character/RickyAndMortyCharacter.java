package com.example.retrofitandroid3.models.character;

import com.google.gson.annotations.SerializedName;

public class RickyAndMortyCharacter {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("status")
    public String status;

     @SerializedName("image")
     public String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


