package com.example.retrofitandroid3.models.episodemodel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity
public class EpisodeRickyAndMorty  {

    @PrimaryKey
    @SerializedName("id")
     public int id;

    @SerializedName("name")
     public String name;

    @SerializedName("episode")
     public String episode;

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

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }
}
