package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Ingredient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngListAdapter extends RecyclerView.Adapter <IngListAdapter.ViewHolder> {
    private ArrayList<Ingredient> ingredients;
    private Context context;

    public IngListAdapter(ArrayList<Ingredient> ingredients, Context context) {
        this.ingredients = ingredients;
        this.context = context;
    }


    @NonNull
    @Override
    public IngListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //Inflate and return the layout
        View ing = layoutInflater.inflate(R.layout.single_ing, parent, false);
        return new IngListAdapter.ViewHolder(ing);
    }

    @Override
    public void onBindViewHolder(@NonNull IngListAdapter.ViewHolder holder, int position) {
        Ingredient i = ingredients.get(position);
        String name = i.getIngredient();
        int quatity = i.getQuantity();
        holder.name.setText(name);
        holder.quantity.setText(String.valueOf(quatity));
        holder.measure.setText(i.getMeasure());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ingr_name) TextView name;
        @BindView(R.id.ing_quantity) TextView quantity;
        @BindView(R.id.ingr_measure) TextView measure;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
