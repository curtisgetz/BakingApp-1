package com.example.android.bakingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VideoFragment extends android.support.v4.app.Fragment {
    @BindView(R.id.description)
    TextView description_text_view;

    @BindView(R.id.playerView)
    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer simpleExoPlayer;
    public long currentPosition = C.TIME_UNSET;
    private String video_url;
    private String step_description;
    Context context;



    public VideoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);
        ButterKnife.bind(this, view);
        context = getActivity().getBaseContext();

         String cake = getActivity().getIntent().getExtras().getString("video");
         String description = getActivity().getIntent().getExtras().getString("description");

        if(savedInstanceState != null) {
            currentPosition = savedInstanceState.getLong("current_position");
        }

        simpleExoPlayerView.setVisibility(View.VISIBLE);
        if (cake != null) {
            initializePlayer(Uri.parse(cake));
        }

        if (video_url != null) {
            initializePlayer(Uri.parse(video_url));
        }

        if (description!= null){
            description_text_view.setText(description);
        }

        if (step_description!= null) {
            description_text_view.setText(step_description);
        }

        return view;
    }

    public void setVideoURL(String videoURL) {
        video_url = videoURL;
    }

    public void setDescription(String description) {
        step_description = description;
    }

    /**
     * Initialize ExoPlayer.
     * @param videoUri The URI of the sample to play.
     */
    private void initializePlayer(Uri videoUri) {
        if (simpleExoPlayer == null) {
            String uriString = videoUri.toString();
            if (uriString.isEmpty()){
                simpleExoPlayerView.setForeground(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.novideo));
            } else {
                // Create an instance of the ExoPlayer.
                TrackSelector trackSelector = new DefaultTrackSelector();
                LoadControl loadControl = new DefaultLoadControl();
                simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
                simpleExoPlayerView.setPlayer(simpleExoPlayer);
                // Prepare the MediaSource.
                String userAgent = Util.getUserAgent(getActivity().getBaseContext(), "BakingApp");
                MediaSource mediaSource = new ExtractorMediaSource(videoUri, new DefaultDataSourceFactory(
                        getActivity().getBaseContext(), userAgent), new DefaultExtractorsFactory(), null, null);
                simpleExoPlayer.prepare(mediaSource);
                simpleExoPlayer.setPlayWhenReady(true);
            }
        }
    }
    /*
     * Release ExoPlayer.
     */
    private void releasePlayer() {
        if (simpleExoPlayer != null){
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(simpleExoPlayer != null) {
            simpleExoPlayer.seekTo(currentPosition);
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        initializePlayer(Uri.parse(video_url));
//    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle currentState) {
        super.onSaveInstanceState(currentState);
        if(simpleExoPlayer != null) {
            currentPosition = simpleExoPlayer.getCurrentPosition();
            currentState.putLong("current_position", currentPosition);
        }
    }

}