package com.example.retrofitandroid3.paging;

import androidx.paging.PagingSource;
import androidx.paging.PagingState;

import com.example.retrofitandroid3.models.character.RickyAndMortyCharacter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.coroutines.Continuation;

public class PagingResource extends PagingSource <Integer, RickyAndMortyCharacter> {


    @Override
    public @Nullable Integer getRefreshKey(@NotNull PagingState<Integer, RickyAndMortyCharacter> pagingState) {
        return null;
    }

    @Override
    public @Nullable Object load(@NotNull LoadParams<Integer> loadParams, @NotNull Continuation<? super LoadResult<Integer, RickyAndMortyCharacter>> continuation) {
        return null;
    }
}
