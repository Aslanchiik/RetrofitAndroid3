package com.example.retrofitandroid3.ui.fragments.loacations;

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

import com.example.retrofitandroid3.databinding.FragmentLocationsBinding;
import com.example.retrofitandroid3.models.location.LocationsRickyAndMorty;
import com.example.retrofitandroid3.models.response.RickAndMortyResponse;
import com.example.retrofitandroid3.ui.adapter.locationadapter.AdapterLocations;
import com.example.retrofitandroid3.viwemodels.loctaionviewmodel.ViewModelLocations;

import org.jetbrains.annotations.NotNull;

public class LocationsFragment extends Fragment {


    FragmentLocationsBinding binding;

    private boolean load = true;

    private int pastItems, visibleItem, totalItem;

    LinearLayoutManager linearLayoutManager;

    public AdapterLocations locations = new AdapterLocations();

    ViewModelLocations viewModelLocations = new ViewModelLocations();


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentLocationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecycler();
        setProvider();

    }

    private void setProvider() {
        viewModelLocations = new ViewModelProvider(this).get(ViewModelLocations.class);
    }

    private void setRecycler() {
        binding.progressBarLocations.setMax(100);
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.locationsRecView.setLayoutManager(linearLayoutManager);
        binding.locationsRecView.setAdapter(locations);

          if (isNetworkAvailable()){
              viewModelLocations.fetchLocation().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<LocationsRickyAndMorty>>() {
                  @Override
                  public void onChanged(RickAndMortyResponse<LocationsRickyAndMorty> locationsRickyAndMortyRickAndMortyResponse) {
                      locations.addList(locationsRickyAndMortyRickAndMortyResponse.getResults());
                      binding.progressBarLocations.setVisibility(View.GONE);
                  }
              });
          }       else   {
               locations.addList(viewModelLocations.getLocationsOver());
               binding.progressBarLocations.setVisibility(View.GONE);
          }
        scrollPage();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
        return activeInfo != null && activeInfo.isConnected();


    }

    private void scrollPage() {
        binding.locationsRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItem = linearLayoutManager.getChildCount();
                    totalItem = linearLayoutManager.getItemCount();
                    pastItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (load) {
                        if ((visibleItem + pastItems) >= totalItem) {
                            load = false;
                            viewModelLocations.characterPage++;

                                 if (isNetworkAvailable()){
                                     binding.progressBarGone.setVisibility(View.VISIBLE);
                                 }
                            viewModelLocations.fetchLocation().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<LocationsRickyAndMorty>>() {
                                @Override
                                public void onChanged(RickAndMortyResponse<LocationsRickyAndMorty> locationsRickyAndMortyRickAndMortyResponse) {
                                    if (locationsRickyAndMortyRickAndMortyResponse != null) {
                                        locations.addList(locationsRickyAndMortyRickAndMortyResponse.getResults());
                                        binding.progressBarGone.setVisibility(View.GONE);
                                    }
                                      else {
                                          locations.addList(viewModelLocations.getLocationsOver());
                                    }
                                }
                            });
                            load = true;
                        }
                    }
                }
            }
        });

    }
}