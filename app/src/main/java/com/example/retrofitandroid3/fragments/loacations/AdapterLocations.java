package com.example.retrofitandroid3.fragments.loacations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitandroid3.databinding.ItemLocationsBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterLocations  extends RecyclerView.Adapter<AdapterLocations.ViewHolder> {


     ItemLocationsBinding binding;
     ArrayList <LocationsRickyAndMorty> list = new ArrayList<>();


      public void addList (ArrayList<LocationsRickyAndMorty> list) {
          this.list.addAll(list);
          notifyDataSetChanged();
      }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding = ItemLocationsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterLocations.ViewHolder holder, int position) {
         holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }

        public void bind(LocationsRickyAndMorty locationsRickyAndMorty) {
                binding.itemLocationsName.setText(locationsRickyAndMorty.name);
                binding.itemType.setText(locationsRickyAndMorty.type);
                binding.itemDesintaion.setText(locationsRickyAndMorty.dimension);
        }
    }
}
