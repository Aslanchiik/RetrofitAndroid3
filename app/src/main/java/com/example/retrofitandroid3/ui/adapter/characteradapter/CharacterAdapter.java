package com.example.retrofitandroid3.ui.adapter.characteradapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitandroid3.data.ItemClick;
import com.example.retrofitandroid3.models.character.RickyAndMortyCharacter;
import com.example.retrofitandroid3.databinding.ItemCharactersBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    ItemCharactersBinding binding;
    private final ArrayList<RickyAndMortyCharacter> list = new ArrayList<>();
    ItemClick click;

    public void setClick(ItemClick click) {
        this.click = click;
    }

//    public CharacterAdapter(@NonNull @NotNull DiffUtil.ItemCallback<RickyAndMortyCharacter> diffCallback) {
//        super(diffCallback);
//    }

    public void addList(ArrayList<RickyAndMortyCharacter> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
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
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder {

        public CharacterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }

        public void bind(RickyAndMortyCharacter item) {
            Glide.with(binding.itemImage).
                    load(item.image).into(binding.itemImage);

            binding.itemText.setText(item.name);

            itemView.setOnClickListener(v -> click.click(item.id, itemView));

        }
    }
}
