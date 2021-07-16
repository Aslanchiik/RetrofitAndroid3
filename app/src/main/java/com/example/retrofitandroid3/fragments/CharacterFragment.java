package com.example.retrofitandroid3.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.retrofitandroid3.BaseFragment;
import com.example.retrofitandroid3.ItemClick;
import com.example.retrofitandroid3.adapter.CharacterAdapter;
import com.example.retrofitandroid3.character.RickyAndMortyCharacter;
import com.example.retrofitandroid3.databinding.FragmentBaseBinding;
import com.example.retrofitandroid3.databinding.FragmentCharacterBinding;
import com.example.retrofitandroid3.models.RickAndMortyResponse;
import com.example.retrofitandroid3.viewmodel.CharacterViewModel;

public class CharacterFragment extends BaseFragment <FragmentCharacterBinding, CharacterViewModel> {

     public CharacterAdapter characterAdapter =  new CharacterAdapter(CharacterAdapter.diffCallback);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }

    @Override
    protected void setupObserves() {
        viewModel.data.observe(getViewLifecycleOwner(), rickyAndMortyCharacterRickAndMortyResponse -> {
            characterAdapter.submitList(rickyAndMortyCharacterRickAndMortyResponse.getResults());
        });
    }

    @Override
    protected void setupRequests() {
        viewModel.fetchCharacters();
    }

    @Override
    protected void setupListener() {
              characterAdapter.setClick(new ItemClick() {
                  @Override
                  public void click(int id, View view) {
                      Navigation.findNavController(view).navigate(CharacterFragmentDirections.actionCharacterFragmentToGetApiDescription(id).setViewModelApi(id));
                  }
              });
    }

    @Override
    protected void setupViews() {
        setupRecycler ();

    }

    private void setupRecycler() {
         binding.recView.setLayoutManager(new LinearLayoutManager(getContext()));
         binding.recView.setAdapter(characterAdapter);
    }

    @Override
    protected void initialize() {
         viewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}