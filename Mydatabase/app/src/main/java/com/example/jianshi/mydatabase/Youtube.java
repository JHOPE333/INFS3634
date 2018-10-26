package com.example.jianshi.mydatabase;

import android.content.Intent;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static android.support.constraint.Constraints.TAG;

public class Youtube extends YouTubeBaseActivity{
    private YouTubePlayerView video;
    private YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_main);
        //get the intent sent from the video class and use the included information to play the appropriate video
        Intent intent = getIntent();
        final String videoID = intent.getStringExtra("VideoID");
        String videoDesc = intent.getStringExtra("VideoDesc");

        //setting up the video player and loading the appropriate video
        video = (YouTubePlayerView) findViewById(R.id.videoView);

        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoID);
                Log.i(TAG, "video played");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.wtf(TAG, "video failed, uh oh");
            }
        };
        TextView description = (TextView)findViewById(R.id.textView2);
        description.setText(videoDesc);
        video.initialize("AIzaSyAfgGIj6JjsCHWgy3W8r9nN8CEWYRvyCh8",listener);

    }
}