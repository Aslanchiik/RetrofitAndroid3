package com.example.retrofitandroid3;

import com.google.gson.annotations.SerializedName;

public class Origin {


    @SerializedName("name")
    public String name;

    @SerializedName("url")
    public String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
