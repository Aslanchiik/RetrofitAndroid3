package com.example.retrofitandroid3.ui.fragments.getapi;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofitandroid3.ui.fragments.basefrag.BaseFragment;
import com.example.retrofitandroid3.databinding.FragmentGetApiDescriptionBinding;
import com.example.retrofitandroid3.viwemodels.getdescription.GetDescriptionViewModel;

import org.jetbrains.annotations.NotNull;


public class GetApiDescription extends BaseFragment <FragmentGetApiDescriptionBinding, GetDescriptionViewModel> {

     FragmentGetApiDescriptionBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGetApiDescriptionBinding.inflate(getLayoutInflater(), container, false);
        return  binding.getRoot();
    }

    @Override
    protected void setupObserves() {
//        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<RickyAndMortyCharacter>() {
//            @Override
//            public void onChanged(RickyAndMortyCharacter rickyAndMortyCharacter) {
//                Glide.with(binding.setImage)
//                        .load(rickyAndMortyCharacter.image)
//                        .into(binding.setImage);
//
//                 binding.textView.setText(rickyAndMortyCharacter.name);
//                 binding.statusText.setText(rickyAndMortyCharacter.status);
//
//            }
//        });
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
        viewModel = new ViewModelProvider(this).get(GetDescriptionViewModel.class);
    }
}