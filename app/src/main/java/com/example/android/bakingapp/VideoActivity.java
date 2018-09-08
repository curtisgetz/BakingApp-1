package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);


        VideoFragment videoFragment = new VideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("video", getIntent().getStringExtra("video") );
        bundle.putString("description", getIntent().getStringExtra("video") );
        bundle.putParcelableArrayList("stepsArrayList", getIntent().getParcelableArrayListExtra("stepsArrayList") );
        bundle.putParcelable("singleStep", getIntent().getParcelableExtra("singleStep") );
        videoFragment.setArguments(bundle);
    }
}