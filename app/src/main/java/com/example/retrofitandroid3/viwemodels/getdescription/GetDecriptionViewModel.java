package com.example.retrofitandroid3.viwemodels.getdescription;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitandroid3.app.App;
import com.example.retrofitandroid3.models.character.RickyAndMortyCharacter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDecriptionViewModel extends ViewModel {

    public MutableLiveData<RickyAndMortyCharacter> liveData = new MutableLiveData<>();

    public void getData(int id) {
        App.charactersApiService.fetchCharacter(id).enqueue(new Callback<RickyAndMortyCharacter>() {
            @Override
            public void onResponse(Call<RickyAndMortyCharacter> call, Response<RickyAndMortyCharacter> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickyAndMortyCharacter> call, Throwable t) {
                liveData.setValue(null);
            }
        });
    }

}
