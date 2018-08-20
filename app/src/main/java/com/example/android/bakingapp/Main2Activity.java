package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.bakingapp.adapters.FragPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity implements StepsFragment.OnClickStepListener {
    FragPageAdapter fragPageAdapter;
    @BindView(R.id.vpPager)
    ViewPager viewPager;
    String videoURLA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);


        fragPageAdapter = new FragPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragPageAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onStepSelected(String videoURL) {
        Toast.makeText(this, videoURL + " From Activity", Toast.LENGTH_SHORT).show();
    }
}
