package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {
//    @BindView(R.id.text_test)
//    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);


        VideoFragment videoFragment = new VideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("video", getIntent().getStringExtra("video") );
        bundle.putString("description", getIntent().getStringExtra("video") );
        videoFragment.setArguments(bundle);
    }
}
