package com.example.retrofitandroid3.ui.fragments.episode;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofitandroid3.databinding.FragmentEpisodeBinding;
import com.example.retrofitandroid3.models.episodemodel.EpisodeRickyAndMorty;
import com.example.retrofitandroid3.models.response.RickAndMortyResponse;
import com.example.retrofitandroid3.ui.adapter.episodeadapter.EpisodeAdapter;
import com.example.retrofitandroid3.viwemodels.episodeviewmodel.ViewEpisodeModel;

import org.jetbrains.annotations.NotNull;


public class EpisodeFragment extends Fragment {

    FragmentEpisodeBinding binding;

    private boolean progress = true;

    private int pastItems, visibleItem, totalItem;

    ViewEpisodeModel episodeModel = new ViewEpisodeModel();

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

    EpisodeAdapter adapter = new EpisodeAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecycler();
        setProvider();
    }

    private void setProvider() {
        episodeModel = new ViewModelProvider(this).get(ViewEpisodeModel.class);
    }

    private void setRecycler() {
        binding.progressBarEpisode.setMax(100);
        binding.episodeRecView.setLayoutManager(linearLayoutManager);
        binding.episodeRecView.setAdapter(adapter);

        if (isNetworkAvailable()) {
            episodeModel.fetchEpisode().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<EpisodeRickyAndMorty>>() {
                @Override
                public void onChanged(RickAndMortyResponse<EpisodeRickyAndMorty> episodeRickyAndMortyRickAndMortyResponse) {
                    adapter.addAllList(episodeRickyAndMortyRickAndMortyResponse.getResults());
                    binding.progressBarEpisode.setVisibility(View.GONE);
                }
            });
        }
         else  {
             adapter.addAllList(episodeModel.getEpisodeOver());
             binding.progressBarEpisode.setVisibility(View.GONE);
        }

        scrollNextPages();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
        return activeInfo != null && activeInfo.isConnected();

    }


    private void scrollNextPages() {
        binding.episodeRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItem = linearLayoutManager.getChildCount();
                    totalItem = linearLayoutManager.getItemCount();
                    pastItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (progress) {
                        if ((visibleItem + pastItems) >= totalItem) {
                            progress = false;
                            episodeModel.characterPage++;

                            if (isNetworkAvailable()) {
                                binding.progressBarEpisodeGone.setVisibility(View.VISIBLE);
                                {

                                    episodeModel.fetchEpisode().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<EpisodeRickyAndMorty>>() {
                                        @Override
                                        public void onChanged(RickAndMortyResponse<EpisodeRickyAndMorty> episodeRickyAndMortyRickAndMortyResponse) {
                                            if (episodeRickyAndMortyRickAndMortyResponse != null) {
                                                adapter.addAllList(episodeRickyAndMortyRickAndMortyResponse.getResults());
                                                binding.progressBarEpisodeGone.setVisibility(View.GONE);
                                            } else
                                                adapter.addAllList(episodeModel.getEpisodeOver());
                                        }
                                    });
                                    progress = true;
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}