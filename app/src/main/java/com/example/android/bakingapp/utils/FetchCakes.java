package com.example.android.bakingapp.utils;

import android.os.AsyncTask;

import com.example.android.bakingapp.model.Cake;

import java.util.ArrayList;

public class FetchCakes extends AsyncTask <String, Void, ArrayList<Cake>> {
    private String to_be_fetched;
    private OnTaskCompleted onTaskCompleted;
    private ArrayList<Cake> cakes = new ArrayList<>();

    public FetchCakes( OnTaskCompleted onTaskCompleted, String to_be_fetched) {
        this.to_be_fetched = to_be_fetched;
        this.onTaskCompleted = onTaskCompleted;
    }

    @Override
    protected ArrayList<Cake> doInBackground(String... strings) {
        cakes = ExecuteJSONUtils.fetchTheCakes(to_be_fetched);
        return cakes;
    }

    @Override
    protected void onPostExecute(ArrayList<Cake> cakes) {
        onTaskCompleted.onTaskCompleted(cakes);
        super.onPostExecute(cakes);
    }
}
