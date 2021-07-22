package com.example.retrofitandroid3.ui.fragments.basefrag;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofitandroid3.databinding.FragmentBaseBinding;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment<Binding, ViewModel> extends Fragment {

    protected Binding binding;
    protected ViewModel viewModel;


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize();
        setupViews();
        setupListener();
        setupRequests();
        setupObserves();
    }

    protected void setupObserves() {

    }

    protected abstract void setupRequests();

    protected abstract void setupListener();

    protected abstract void setupViews();

    protected abstract void initialize();
}
