package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.adapters.FragPageAdapter;
import com.example.android.bakingapp.model.Step;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity implements StepsFragment.OnClickStepListener {
    FragPageAdapter fragPageAdapter;
    @BindView(R.id.vpPager)
    @Nullable ViewPager viewPager;
    boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        if ( findViewById(R.id.all_item_holder) != null ) {
            viewPager = null;
        } else {
            twoPane = false;

            fragPageAdapter = new FragPageAdapter(getSupportFragmentManager());
            viewPager.setAdapter(fragPageAdapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void onStepSelected(ArrayList<Step> steps, Step single_step) {
        if ( findViewById(R.id.all_item_holder) != null ) {
//            viewPager = null;
//            twoPane = true;
//            //Toast.makeText(this, videoURL + "from activity", Toast.LENGTH_SHORT).show();
//            VideoFragment videoFragment = new VideoFragment();
//            videoFragment.setVideoURL(step.getVideoURL());
//            videoFragment.setDescription(step.getDescription());
//            getSupportFragmentManager().beginTransaction().replace(R.id.container, videoFragment).commit();
        } else {
            twoPane = false;
            Intent intent = new Intent(this, VideoActivity.class);
            intent.putExtra("stepsArrayList", steps);
            intent.putExtra("singleStep", single_step); //trying to ADD the individual steps as well as teh whole list

            intent.putExtra("video", "goooogle.com");
            intent.putExtra("description", "descccrrrppp");

            startActivity(intent);
        }
    }

}
