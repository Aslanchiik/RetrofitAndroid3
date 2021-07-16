package com.example.retrofitandroid3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitandroid3.ItemClick;
import com.example.retrofitandroid3.character.RickyAndMortyCharacter;
import com.example.retrofitandroid3.databinding.ItemCharactersBinding;

import org.jetbrains.annotations.NotNull;

public class CharacterAdapter extends ListAdapter<RickyAndMortyCharacter, CharacterAdapter.CharacterViewHolder> {

     ItemCharactersBinding binding;
     ItemClick click;

    public void setClick(ItemClick click) {
        this.click = click;
    }

    public CharacterAdapter(@NonNull @NotNull DiffUtil.ItemCallback<RickyAndMortyCharacter> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @NotNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
         binding = ItemCharactersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CharacterViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CharacterAdapter.CharacterViewHolder holder, int position) {
               holder.bind(getItem(position));
    }
     public static DiffUtil.ItemCallback <RickyAndMortyCharacter> diffCallback = new DiffUtil.ItemCallback<RickyAndMortyCharacter>() {
         @Override
         public boolean areItemsTheSame(@NonNull @NotNull RickyAndMortyCharacter oldItem, @NonNull @NotNull RickyAndMortyCharacter newItem) {
             return false;
         }

         @Override
         public boolean areContentsTheSame(@NonNull @NotNull RickyAndMortyCharacter oldItem, @NonNull @NotNull RickyAndMortyCharacter newItem) {
             return false;
         }
     };

    public class CharacterViewHolder extends RecyclerView.ViewHolder {

        public CharacterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }

        public void bind(RickyAndMortyCharacter item) {
            Glide.with(binding.itemImage).
                    load(item.image).into(binding.itemImage);

             binding.itemText.setText(item.name);

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     click.click(item.id, itemView);
                 }
             });

        }
    }
}
