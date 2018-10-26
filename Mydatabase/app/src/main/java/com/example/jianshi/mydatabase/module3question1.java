package com.example.jianshi.mydatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class module3question1 extends android.support.v4.app.Fragment {
    private TextView question1;
    private TextView percentTextView;
    private Button Checkbtn;
    private RadioButton radioButton;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioGroup radioGroup;
    private ProgressBar progressBar;
    private Button incorrect;
    private Button correct;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_question3, container, false);
        question1 = view.findViewById(R.id.question1);
        Checkbtn = view.findViewById(R.id.Check);
        radioGroup = view.findViewById(R.id.RadioGroup);
        progressBar = view.findViewById(R.id.progressBar);
        percentTextView = view.findViewById(R.id.percentTextview);

        radioButton1 = view.findViewById(R.id.radioButton1);
        radioButton1.setText("Stores the metadata of the tables in database");

        radioButton2 = view.findViewById(R.id.radioButton2);
        radioButton2.setText("Stores results from insert query");

        radioButton4 = view.findViewById(R.id.radioButton4);
        radioButton4.setText("An object that stores data sent back from the database");

        radioButton3 = view.findViewById(R.id.radioButton3);
        radioButton3.setText("None of the above ");
        incorrect = view.findViewById(R.id.incorrect);
        correct = view.findViewById(R.id.correct);
        incorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new module3generalinfo());
            }
        });
        incorrect.setVisibility(View.GONE);
        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new module3question2());
            }
        });
        correct.setVisibility(View.GONE);

        //creates progress bar at 25%
        progressBar.setProgress(0);
        percentTextView.setText(Integer.toString(progressBar.getProgress()) + "%");

        // retrieves question 1 from db and shows on textview
        new getQuestion10().execute(10);

        // button that checks if ur choice is the right answer
        Checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new checkAnswer10().execute(10);
            }
        });
        return view;
    }

    //retrieves question 1
    public class getQuestion10 extends AsyncTask<Integer, Void, String> {


        @Override
        protected String doInBackground(Integer... integers) {
            if(integers.length == 0) {
                cancel(true);
            }
            return QuestionDatabase.db.userDao().loadAllbyIds(integers[0]);



        }
        @Override
        protected void onPostExecute(String question) {

            question1.setText(question);
        }

    }
    //checks if ur choice is the right answer
    class checkAnswer10 extends AsyncTask <Integer, Void, String> {
        @Override
        protected String doInBackground(Integer... integers) {

            return QuestionDatabase.db.userDao().getAnswer(integers[0]);

        }
        @Override
        protected void onPostExecute(String answer) {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = view.findViewById(selectedId);
            try {
                if (radioButton.getText().toString().equals(answer)) {
                    Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();
                    correct.setVisibility(view.VISIBLE);
                    Checkbtn.setVisibility(view.GONE);
                    incorrect.setVisibility(view.GONE);
                } else {
                    incorrect.setVisibility(view.VISIBLE);
                    Toast.makeText(getContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                }
            } catch (NullPointerException e) {
                Toast.makeText(getContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
            }

        }
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
