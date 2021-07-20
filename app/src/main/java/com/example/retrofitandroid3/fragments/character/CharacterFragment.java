package com.example.retrofitandroid3.fragments.character;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitandroid3.BaseFragment;
import com.example.retrofitandroid3.inter.ItemClick;
import com.example.retrofitandroid3.adapter.CharacterAdapter;
import com.example.retrofitandroid3.databinding.FragmentCharacterBinding;
import com.example.retrofitandroid3.models.viewmodel.CharacterViewModel;

import org.jetbrains.annotations.NotNull;

public class CharacterFragment extends BaseFragment<FragmentCharacterBinding, CharacterViewModel> {

    private boolean loading = true;

    private int pastVisiblesItems, visibleItemCount, totalItemCount;

    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

    public CharacterAdapter characterAdapter = new CharacterAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

//    @Override
//    protected void setupObserves() {
//        viewModel.data.observe(getViewLifecycleOwner(), rickyAndMortyCharacterRickAndMortyResponse -> {
//        });
//    }

    @Override
    protected void setupRequests() {
        viewModel.fetchCharacters().observe(getViewLifecycleOwner(), rickyAndMortyCharacterRickAndMortyResponse -> {
            characterAdapter.addList(rickyAndMortyCharacterRickAndMortyResponse.getResults());
            binding.progressBar.setVisibility(View.GONE);

        });
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
        setupRecycler();

    }

    private void setupRecycler() {
        binding.progressBar.setMax(100);
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
                         if ((visibleItemCount + pastVisiblesItems) >= totalItemCount ) {
                             loading = false;
                             viewModel.characterPage++;
                             viewModel.fetchCharacters().observe(getViewLifecycleOwner(), rickyAndMortyCharacterRickAndMortyResponse ->
                                     characterAdapter.addList(rickyAndMortyCharacterRickAndMortyResponse.getResults()));
                             loading =true;
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