package com.example.retrofitandroid3.fragments.character;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitandroid3.BaseFragment;
import com.example.retrofitandroid3.inter.ItemClick;
import com.example.retrofitandroid3.adapter.CharacterAdapter;
import com.example.retrofitandroid3.databinding.FragmentCharacterBinding;
import com.example.retrofitandroid3.models.character.RickyAndMortyCharacter;
import com.example.retrofitandroid3.models.response.RickAndMortyResponse;
import com.example.retrofitandroid3.models.viewmodel.CharacterViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;

public class CharacterFragment extends BaseFragment<FragmentCharacterBinding, CharacterViewModel> {

    private boolean loading = true;

    private int pastVisiblesItems, visibleItemCount, totalItemCount;

    int counter = 0;

    private LinearLayoutManager linearLayoutManager;

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
        characterAdapter.setClick((id, view) ->
                Navigation.findNavController(view).navigate(CharacterFragmentDirections.actionCharacterFragmentToGetApiDescription(id).setViewModelApi(id)));
    }

    @Override
    protected void setupViews() {
        setupRecycler();

    }

    private void setupRecycler() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        addProgressBar();
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
                            binding.progressBarCharacter.setVisibility(View.VISIBLE);
                            viewModel.fetchCharacters().observe(getViewLifecycleOwner(), rickyAndMortyCharacterRickAndMortyResponse -> {
                                if (rickyAndMortyCharacterRickAndMortyResponse != null){
                                    characterAdapter.addList(rickyAndMortyCharacterRickAndMortyResponse.getResults());
                                    binding.progressBarCharacter.setVisibility(View.GONE);
                                }
                                 else
                                     binding.progressBarCharacter.setVisibility(View.GONE);
                            });
                            loading = true;

                        }
                    }
                }
            }
        });
    }

    private void addProgressBar() {
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                binding.progressBar.setProgress(counter);

                if (counter == 15)
                    t.cancel();

        }};
        t.schedule(tt, 0, 15);
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