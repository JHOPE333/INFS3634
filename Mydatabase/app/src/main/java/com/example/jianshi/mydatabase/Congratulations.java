package com.example.jianshi.mydatabase;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
//this class is shown when user finishes a module
public class Congratulations extends android.support.v4.app.Fragment {
    View view;
    Button home;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.congratulations, container, false);
        home = view.findViewById(R.id.button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pops the backstack, not letting the user revisit their questions by pressing the back button as they have already completed the quiz
                FragmentManager fm = getActivity().getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                loadFragment(new QuestionDatabase());
            }
        });
        return view;
    }
    public void loadFragment(android.support.v4.app.Fragment fragment){
        // create a fragment manager and FragmentTransaction to begin the transaction and replace the Fragment, backstack is used to ensure back button presses function normally by popping frags off the stack
        android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction().add(fragment, "detail").addToBackStack(null);
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,R.anim.enter_from_left, R.anim.exit_to_right );
        // replace the Base Layout with new Fragment
        transaction.replace(R.id.constraint_layout, fragment);
        // save the changes
        transaction.commit();
    }
}
