package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakingapp.adapters.CakeListAdapter;
import com.example.android.bakingapp.model.Cake;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.model.Step;
import com.example.android.bakingapp.utils.FetchCakes;
import com.example.android.bakingapp.utils.OnTaskCompleted;
import com.example.android.bakingapp.utils.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.bakingapp.utils.Utility.ing_size_id_one;
import static com.example.android.bakingapp.utils.Utility.ing_size_id_three;
import static com.example.android.bakingapp.utils.Utility.ing_size_id_two;
import static com.example.android.bakingapp.utils.Utility.step_size_id_one;
import static com.example.android.bakingapp.utils.Utility.step_size_id_three;
import static com.example.android.bakingapp.utils.Utility.step_size_id_two;

public class MainActivity extends AppCompatActivity implements OnTaskCompleted {
    @BindView(R.id.rv_cake)
    RecyclerView rv_cake;
    CakeListAdapter cakeListAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList <Cake> cakes_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //ArrayList <String> cakes = new ArrayList<>();

        FetchCakes cakesTask = new FetchCakes(MainActivity.this, Utility.prepareString());
        cakesTask.execute();

    }

    @Override
    public void onTaskCompleted(ArrayList<Cake> cakes) {
        cakes_list = cakes;
        for (int i = 0; i < cakes_list.size(); i++ ) {
            Cake cake = cakes.get(i);
            if (cake.getId() == 1){
                List<Ingredient> first_ing = cake.getIngredients().subList(0,ing_size_id_one);
                ArrayList <Ingredient> x = new ArrayList<Ingredient>(first_ing);
                cake.setIngredients(x);

                List<Step> first_step = cake.getSteps().subList(0,step_size_id_one);
                ArrayList <Step> y = new ArrayList<Step>(first_step);
                cake.setSteps(y);

            } else if (cake.getId() == 2){
                List<Ingredient> sec_ing = cake.getIngredients().subList(ing_size_id_one, ing_size_id_two+ing_size_id_one);
                ArrayList <Ingredient> x = new ArrayList<Ingredient>(sec_ing);
                cake.setIngredients(x);

                List<Step> sec_step = cake.getSteps().subList(step_size_id_one, step_size_id_two+step_size_id_one);
                ArrayList <Step> y = new ArrayList<Step>(sec_step);
                cake.setSteps(y);

            } else if (cake.getId() == 3){
                List<Ingredient> third_ing = cake.getIngredients().subList(ing_size_id_two + ing_size_id_one, ing_size_id_three + ing_size_id_two + ing_size_id_one);
                ArrayList <Ingredient> x = new ArrayList<Ingredient>(third_ing);
                cake.setIngredients(x);

                List<Step> sec_step = cake.getSteps().subList(step_size_id_two + step_size_id_one, step_size_id_two + step_size_id_one + step_size_id_three);
                ArrayList <Step> y = new ArrayList<Step>(sec_step);
                cake.setSteps(y);
            } else {
                List<Ingredient> last_ing = cake.getIngredients().subList(ing_size_id_three+ ing_size_id_two+ing_size_id_one, cake.getIngredients().size());
                ArrayList <Ingredient> x = new ArrayList<Ingredient>(last_ing);
                cake.setIngredients(x);

                List<Step> sec_step = cake.getSteps().subList(step_size_id_two+step_size_id_one+step_size_id_three, cake.getSteps().size() );
                ArrayList <Step> y = new ArrayList<Step>(sec_step);
                cake.setSteps(y);
            }
        }

        cakes_list = cakes;
        ArrayList <String> cake_name = new ArrayList<>();

        for (int x = 0; x < cakes_list.size(); x++){
            Cake cake = cakes.get(x);
            String name = cake.getName();
            cake_name.add(name);
        }
        cakeListAdapter = new CakeListAdapter(cake_name, cakes, getApplicationContext());
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_cake.setLayoutManager(layoutManager);
        rv_cake.setAdapter(cakeListAdapter);
    }
}
