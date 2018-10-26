package com.example.jianshi.mydatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.support.constraint.Constraints.TAG;

public class Help extends android.support.v4.app.Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.help_main, container, false);

        TextView content = view.findViewById(R.id.content);
        content.setText(R.string.help_content);
        Log.i(TAG, "help created");
        return view;
    }
}
