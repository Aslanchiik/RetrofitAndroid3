package com.example.retrofitandroid3.ui.adapter.episodeadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitandroid3.databinding.ItemEpisodeBinding;
import com.example.retrofitandroid3.models.episodemodel.EpisodeRickyAndMorty;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {

    ItemEpisodeBinding binding;
    ArrayList <EpisodeRickyAndMorty> list = new ArrayList<>();

     public void addAllList (ArrayList <EpisodeRickyAndMorty> list){
         this.list.addAll(list);
         notifyDataSetChanged();
     }


    @NonNull
    @NotNull
    @Override
    public EpisodeAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EpisodeAdapter.ViewHolder holder, int position) {
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

        public void bind(EpisodeRickyAndMorty episodeRickyAndMorty) {

              binding.itemEpisodeText.setText(episodeRickyAndMorty.name);
              binding.itemPageNumber.setText(episodeRickyAndMorty.episode);
        }
    }
}
