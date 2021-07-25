package com.example.retrofitandroid3.ui.fragments.character;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitandroid3.ui.fragments.basefrag.BaseFragment;
import com.example.retrofitandroid3.ui.adapter.characteradapter.CharacterAdapter;
import com.example.retrofitandroid3.databinding.FragmentCharacterBinding;
import com.example.retrofitandroid3.viwemodels.charactervieewmodel.CharacterViewModel;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CharacterFragment extends BaseFragment<FragmentCharacterBinding, CharacterViewModel> {

    private boolean loading = true;

    private int pastVisiblesItems, visibleItemCount, totalItemCount;

    private LinearLayoutManager linearLayoutManager;

    public CharacterAdapter characterAdapter = new CharacterAdapter();

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    protected void setupRequests() {

        if (isNetworkAvailable()) {
            viewModel.fetchCharacters().observe(getViewLifecycleOwner(), rickyAndMortyCharacterRickAndMortyResponse -> {
                // updateProgress();
                if (rickyAndMortyCharacterRickAndMortyResponse != null) {
                    characterAdapter.addList(rickyAndMortyCharacterRickAndMortyResponse.getResults());
                }
                binding.progressBar.setVisibility(View.GONE);
            });
        } else {
            characterAdapter.addList(viewModel.getCharacters());
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
        return activeInfo != null && activeInfo.isConnected();

    }

    @Override
    protected void setupListener() {
        characterAdapter.setClick((id, view) ->
                Navigation.findNavController(view).navigate((NavDirections) CharacterFragmentDirections.actionCharacterFragmentToGetApiDescription(id).setViewModelApi(id)));
    }

    @Override
    protected void setupViews() {
        setupRecycler();
    }

    private void setupRecycler() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recView.setLayoutManager(linearLayoutManager);
        binding.recView.setAdapter(characterAdapter);
        binding.recView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            viewModel.characterPage++;

                            if (isNetworkAvailable()) {
                                binding.progressBarCharacter.setVisibility(View.VISIBLE);
                            }
                            viewModel.fetchCharacters().observe(getViewLifecycleOwner(), rickyAndMortyCharacterRickAndMortyResponse -> {
                                if (rickyAndMortyCharacterRickAndMortyResponse != null) {
                                    characterAdapter.addList(rickyAndMortyCharacterRickAndMortyResponse.getResults());
                                } else

                                binding.progressBarCharacter.setVisibility(View.GONE);
                            });
                            loading = true;
                        }
                    }
                }
            }
        });
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