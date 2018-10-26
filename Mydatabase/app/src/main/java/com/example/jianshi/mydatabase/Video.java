package com.example.jianshi.mydatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static android.support.constraint.Constraints.TAG;

public class Video extends android.support.v4.app.Fragment {
    View view;
    Button firstFragment, secondFragment, thirdFragment;
    private YouTubePlayerView video;
    private YouTubePlayer.OnInitializedListener listener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.video_main, container, false);
        firstFragment = (Button) view.findViewById(R.id.creatingDatabases);
        secondFragment = (Button) view.findViewById(R.id.makingQueries);
        thirdFragment = (Button) view.findViewById(R.id.databaseIntro);
        Log.i(TAG, "video class");
        //setting the onclicklistners for the three fragments which will direct the user to the appropriate video by using putextra to send info
        firstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load First Fragment
                Intent videoIntent = new Intent(getActivity(), Youtube.class);
                videoIntent.putExtra( "VideoID",getString(R.string.database_vid));
                videoIntent.putExtra( "VideoDesc",getString(R.string.database_desc));
                startActivity(videoIntent);
            }
        });
        secondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load Second Fragment
                Intent videoIntent = new Intent(getActivity(), Youtube.class);
                videoIntent.putExtra( "VideoID",getString(R.string.queries_vid));
                videoIntent.putExtra( "VideoDesc",getString(R.string.queries_desc));
                startActivity(videoIntent);
            }
        });
        thirdFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load Third Fragment
                Intent videoIntent = new Intent(getActivity(), Youtube.class);
                videoIntent.putExtra( "VideoID",getString(R.string.start_vid));
                videoIntent.putExtra( "VideoDesc",getString(R.string.start_desc));
                startActivity(videoIntent);
            }
        });

    return view;
    }
}