package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.ViewHolder> {
    private ArrayList<Step> steps = new ArrayList<>();
    private Context context;
    MyClickListener myClickListener;


    public StepListAdapter(ArrayList<Step> steps, Context context, MyClickListener myClickListener) {
        this.steps = steps;
        this.context = context;
        this.myClickListener = myClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View step = layoutInflater.inflate(R.layout.single_step, parent, false);
        return new StepListAdapter.ViewHolder(step);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Step s = steps.get(position);
        String short_des = s.getShortDescription();

        holder.short_des.setText(short_des);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    /*
     Interface to be implemented
      */
    public interface MyClickListener {
        void onStepSelected(String videoURL);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.short_des)
        TextView short_des;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final Step step = steps.get(getAdapterPosition());

            if ( myClickListener != null ) {
                myClickListener.onStepSelected(step.getVideoURL());
            } else {
                Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
