package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.adapters.IngListAdapter;
import com.example.android.bakingapp.model.Cake;
import com.example.android.bakingapp.model.Ingredient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientFragment extends Fragment {

    @BindView(R.id.rv_ing)
    RecyclerView rv_ing;
    IngListAdapter ingListAdapter;

    ArrayList <Ingredient> ingredients = new ArrayList<>();

    public IngredientFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredient_fragment, container, false);
        ButterKnife.bind(this, view);
        Cake cake = getActivity().getIntent().getExtras().getParcelable("Cakes");
        ingredients = cake.getIngredients();

        ingListAdapter = new IngListAdapter(ingredients, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_ing.setLayoutManager(layoutManager);
        rv_ing.setAdapter(ingListAdapter);
        return view;
    }
}
