package com.example.retrofitandroid3.ui.fragments.character;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitandroid3.ui.fragments.basefrag.BaseFragment;
import com.example.retrofitandroid3.ui.adapter.characteradapter.CharacterAdapter;
import com.example.retrofitandroid3.databinding.FragmentCharacterBinding;
import com.example.retrofitandroid3.viwemodels.charactervieewmodel.CharacterViewModel;

import org.jetbrains.annotations.NotNull;

public class CharacterFragment extends BaseFragment<FragmentCharacterBinding, CharacterViewModel> {

    private boolean loading = true;

    Handler handler;

    private int pastVisiblesItems, visibleItemCount, totalItemCount;

    int counter = 0;

    private LinearLayoutManager linearLayoutManager;

    public CharacterAdapter characterAdapter = new CharacterAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        handler = new Handler();
        return binding.getRoot();
    }


    @Override
    protected void setupRequests() {
        if (isNetworkAvailable()) {
            viewModel.fetchCharacters().observe(getViewLifecycleOwner(), rickyAndMortyCharacterRickAndMortyResponse -> {
                // updateProgress();
                characterAdapter.addList(rickyAndMortyCharacterRickAndMortyResponse.getResults());
                binding.progressBar.setVisibility(View.GONE);
            });
        }
         else {
             characterAdapter.addList(viewModel.getCharacters());
             binding.progressBar.setVisibility(View.GONE);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
        return activeInfo != null && activeInfo.isConnected();

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
                                    binding.progressBarCharacter.setVisibility(View.GONE);
                                } else {
                                    characterAdapter.addList(viewModel.getCharacters());
                                }
                            });
                            loading = true;
                        }
                    }
                }
            }
        });
    }


    private void updateProgress() {

//         if (counter < 100) {
//             counter += 20;
//             binding.progressBar.setDonut_progress(String.valueOf(counter));
//         }
//
//         new Thread(new Runnable() {
//             @Override
//             public void run() {
//                 while (counter < 100){
//                     handler.post(new Runnable() {
//                         @Override
//                         public void run() {
//                             binding.progressBar.setDonut_progress(String.valueOf(counter));
//                         }
//                     });
//                       try {
//                           Thread.sleep(100);
//                       } catch (InterruptedException e) {
//                           e.printStackTrace();
//                       }
//                         counter ++ ;
//                 }
//             }
//         });
//
//          if (counter >= 100){
//              counter +=5;
//              binding.progressBar.setDonut_progress(String.valueOf(counter));
//
//          }
//        for (int i = 0; i < 21; i++) {
//                counter += 2;
//                binding.progressBar.setDonut_progress(String.valueOf(i + counter));
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