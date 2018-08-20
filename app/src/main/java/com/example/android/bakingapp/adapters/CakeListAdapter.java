package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.Main2Activity;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Cake;

import java.util.ArrayList;

public class CakeListAdapter extends RecyclerView.Adapter <CakeListAdapter.ViewHolder> {

    private final ArrayList<String> cakes_name;
    private final ArrayList<Cake> cakes;
    private Context context;

    public CakeListAdapter(ArrayList<String> cakes_name, ArrayList<Cake> cakes, Context context) {
        this.cakes_name = cakes_name;
        this.context = context;
        this.cakes = cakes;

    }

    @NonNull
    @Override
    public CakeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //Inflate and return the layout
        View cake = layoutInflater.inflate(R.layout.single_cake, parent, false);
        return new CakeListAdapter.ViewHolder(cake);
    }

    @Override
    public void onBindViewHolder(@NonNull CakeListAdapter.ViewHolder holder, int position) {
        String cake = cakes_name.get(position);
        holder.cakeTextView.setText(cake);
    }

    @Override
    public int getItemCount() {
        return cakes_name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View parentView;
        private TextView cakeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.parentView = itemView;
            cakeTextView = itemView.findViewById(R.id.cake_tv);
            this.parentView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           Intent intent = new Intent(context, Main2Activity.class);
           Cake cake = cakes.get(getAdapterPosition());
           intent.putExtra("Cakes", cake);
           context.startActivity(intent);
        }
    }
}
