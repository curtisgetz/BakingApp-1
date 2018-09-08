package com.example.android.bakingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bakingapp.model.Step;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VideoFragment extends android.support.v4.app.Fragment {
    private static final String STATE = "STATE";
    private static final String VIDEO = "VIDEO";
    private static final String POSITION = "POS";

    private static final String STEP = "STEP";
    private static final String STEPS = "STEPS";


    public long currentPosition = C.TIME_UNSET;
    @BindView(R.id.description)
    TextView description_text_view;
    @BindView(R.id.next_button)
    ImageView next_button;
    @BindView(R.id.prev_button)
    ImageView prev_button;
    @BindView(R.id.playerView)
    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer simpleExoPlayer;
    Context context;
    Step step;
    ArrayList<Step> stepArrayList;
    int pos;
    String video_to_be_saved;
    private String video_url;
    private String step_description;
    private long position;
    private boolean state = true;
    private String videoUrl;
    long video_pos;
    String video_to_be_resumed;

    public VideoFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);
        ButterKnife.bind(this, view);
        context = getActivity().getBaseContext();

        if ( savedInstanceState != null ) {
            initializePlayer(Uri.parse(savedInstanceState.getString(VIDEO)));
        } else {
            step = getActivity().getIntent().getExtras().getParcelable("singleStep");
            stepArrayList = getActivity().getIntent().getExtras().getParcelableArrayList("stepsArrayList");
            pos = stepArrayList.indexOf(step);
            videoUrl = step.getVideoURL();
        }


        simpleExoPlayerView.setVisibility(View.VISIBLE);
        if ( step != null ) {
            if ( step.getVideoURL().isEmpty() ) {
                simpleExoPlayerView.setVisibility(View.GONE);
                releasePlayer();
            }
            video_to_be_saved = step.getVideoURL();
            initializePlayer(Uri.parse(step.getVideoURL()));
            description_text_view.setText(step.getDescription());


        }

        if ( video_url != null ) {
            initializePlayer(Uri.parse(video_url));
        }

        if ( step_description != null ) {
            description_text_view.setText(step_description);
        }

        if ( next_button != null ) {

            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    releasePlayer();
                    if ( pos < stepArrayList.size() - 1 ) {
                        pos++;
                    } else {
                        Toast.makeText(getContext(), "You have reached the final step", Toast.LENGTH_SHORT).show();
                    }
                    Step step = stepArrayList.get(pos);
                    String video = step.getVideoURL();
                    video_to_be_saved = video;
                    String dis = step.getDescription();
                    description_text_view.setText(dis);
                    if ( video.isEmpty() ) {
                        simpleExoPlayerView.setVisibility(View.GONE);
                        releasePlayer();
                    } else {
                        Uri video_uri = Uri.parse(video);
                        simpleExoPlayerView.setVisibility(View.VISIBLE);
                        initializePlayer(video_uri);
                    }

                }
            });


        }

        if ( prev_button != null ) {
            prev_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    releasePlayer();
                    if ( pos < stepArrayList.size() - 1 ) {
                        pos--;
                    }
                    if ( pos < 0 ) {
                        pos = 0;
                        Toast.makeText(getContext(), "You have reached the first step", Toast.LENGTH_SHORT).show();
                    }
                    Step step = stepArrayList.get(pos);
                    String video = step.getVideoURL();
                    String dis = step.getDescription();
                    description_text_view.setText(dis);
                    if ( video.isEmpty() ) {
                        simpleExoPlayerView.setVisibility(View.GONE);
                        releasePlayer();
                    } else {
                        Uri video_uri = Uri.parse(video);
                        simpleExoPlayerView.setVisibility(View.VISIBLE);
                        initializePlayer(video_uri);
                    }
                }
            });
        }

        return view;
    }

    public void setVideoURL(String videoURL) {
        video_url = videoURL;
    }

    public void setDescription(String description) {
        step_description = description;
    }

    private int getPos(Step step) {
        return stepArrayList.indexOf(step);
    }

    /**
     * Initialize ExoPlayer.
     *
     * @param videoUri The URI of the sample to play.
     */
    private void initializePlayer(Uri videoUri) {
        if ( simpleExoPlayer == null ) {
            String uriString = videoUri.toString();
            if ( uriString.isEmpty() ) {
                Toast.makeText(context, "No Video", Toast.LENGTH_SHORT).show();
            } else {
                // Create an instance of the ExoPlayer.
                TrackSelector trackSelector = new DefaultTrackSelector();
                LoadControl loadControl = new DefaultLoadControl();
                if (getContext() != null ) {
                    simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
                    simpleExoPlayerView.setPlayer(simpleExoPlayer);

                    // Prepare the MediaSource.
                    String userAgent = Util.getUserAgent(getContext(), "BakingApp");
                    if ( videoUri != null ) {
                        MediaSource mediaSource = new ExtractorMediaSource(videoUri, new DefaultDataSourceFactory(
                                getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
                        if ( currentPosition != C.TIME_UNSET )
                            simpleExoPlayer.seekTo(currentPosition);
                        simpleExoPlayer.prepare(mediaSource);
                        simpleExoPlayer.setPlayWhenReady(true);
                    }
                }
            }
        }
    }

    /*
     * Release ExoPlayer.
     */
    private void releasePlayer() {
        if ( simpleExoPlayer != null ) {
            currentPosition = simpleExoPlayer.getCurrentPosition();
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle currentState) {
        currentState.putString(VIDEO, videoUrl);
        super.onSaveInstanceState(currentState);

    }

}

