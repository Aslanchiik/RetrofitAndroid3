package com.example.retrofitandroid3.getapi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.retrofitandroid3.BaseFragment;
import com.example.retrofitandroid3.R;
import com.example.retrofitandroid3.character.RickyAndMortyCharacter;
import com.example.retrofitandroid3.databinding.FragmentGetApiDescriptionBinding;


public class GetApiDescription extends BaseFragment <FragmentGetApiDescriptionBinding,GetDecriptionViewModel> {

     FragmentGetApiDescriptionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGetApiDescriptionBinding.inflate(getLayoutInflater(), container, false);
        return  binding.getRoot();
    }

    @Override
    protected void setupObserves() {
        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<RickyAndMortyCharacter>() {
            @Override
            public void onChanged(RickyAndMortyCharacter rickyAndMortyCharacter) {
                Glide.with(binding.setImage)
                        .load(rickyAndMortyCharacter.image)
                        .into(binding.setImage);

                 binding.textView.setText(rickyAndMortyCharacter.name);

            }
        });
    }

    @Override
    protected void setupRequests() {

    }

    @Override
    protected void setupListener() {

    }

    @Override
    protected void setupViews() {
        int kill = GetApiDescriptionArgs.fromBundle(getArguments()).getViewModelApi();
        viewModel.getData(kill);
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(GetDecriptionViewModel.class);
    }
}