package com.example.retrofitandroid3.models;

import android.icu.text.IDNA;

import com.example.retrofitandroid3.info.Info;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RickAndMortyResponse <T>{

    @SerializedName("info")
     private Info info;

    @SerializedName("results")
    private ArrayList <T> results;

    public void setInfo(Info info) {
        this.info = info;
    }

    public void setResults(ArrayList<T> results) {
        this.results = results;
    }

    public ArrayList<T> getResults() {
        return results;
    }

     public Info getInfo(){
         return info;
     }

}
