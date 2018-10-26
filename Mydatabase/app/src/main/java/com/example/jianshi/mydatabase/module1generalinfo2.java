package com.example.jianshi.mydatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class module1generalinfo2 extends android.support.v4.app.Fragment {
private Button nextBtn;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_module1_info2, container, false);
        nextBtn = view.findViewById(R.id.nextBtn1);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new question1());
            }
        });
        return view;
    }
    public void loadFragment(android.support.v4.app.Fragment fragment){
        // create a fragment manager and FragmentTransaction to begin the transaction and replace the Fragment, backstack is used to ensure back button presses function normally by popping frags off the stack
        android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction().add(fragment, "detail").addToBackStack(null);
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,R.anim.enter_from_left, R.anim.exit_to_right ); // replace the Base Layout with new Fragment
        transaction.replace(R.id.constraint_layout, fragment);
        // save the changes
        transaction.commit();
    }
}
