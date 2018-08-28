package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.adapters.FragPageAdapter;

import org.jetbrains.annotations.Nullable;

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

        if (findViewById(R.id.all_item_holder) != null) {
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
    public void onStepSelected(String videoURL, String description) {
        if (findViewById(R.id.all_item_holder) != null) {
            viewPager = null;
            twoPane = true;
            //Toast.makeText(this, videoURL + "from activity", Toast.LENGTH_SHORT).show();
            VideoFragment videoFragment = new VideoFragment();
            videoFragment.setVideoURL(videoURL);
            videoFragment.setDescription(description);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, videoFragment).commit();


        } else {
            twoPane = false;
            Intent intent = new Intent(this, VideoActivity.class);
            intent.putExtra("video", videoURL);
            intent.putExtra("description", description);
            startActivity(intent);
        }
    }
}
