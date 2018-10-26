package com.example.jianshi.mydatabase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.support.constraint.Constraints.TAG;

public class QuestionDatabase extends android.support.v4.app.Fragment  {
    View view;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    static AppDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.questions_content, container, false);
        //initialise the room database
        db = Room.databaseBuilder(view.getContext(), AppDatabase.class, "database-name").addCallback(new RoomDatabase.Callback() {
            @Override
            // when database is created inputs all question data
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Log.i(TAG, "database built");
                //u can add as many questions just add a comma, primary key autoincrements so need to add
                new InsertQuestion().execute(storeQuestion());
                Log.i(TAG, "inserted");
              //  new InsertModules().execute();


            }
        }).build();
        btn1 = view.findViewById(R.id.Module1btn);

        btn2 = view.findViewById(R.id.Module2btn);

        btn3 = view.findViewById(R.id.Module3btn);

        //Click on module one to go to question 1
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // view.setVisibility(View.GONE);
                loadFragment(new module1Generalinfo());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new module2Generalinfo());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new module3generalinfo());
            }
        });

        //executes a dummy query so that the database is created
        new Dummy().execute();

        return view;
    }
    //Insert query to insert questions & answers into database
    class InsertQuestion extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String...strings) {
            for (int i = 0; i< strings.length; i=i+2) {
                Question question = new Question (strings[i], strings[i+1]);
                db.userDao().insertAll(question);
                System.out.println("length " + strings.length);
            }
            return null;
        }

    }
    //dummy query to initialise database
    class Dummy extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void...voids) {
            db.beginTransaction();
            db.endTransaction();
            return null;
        }

    }
  /*  class InsertModules extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String...strings) {
            ModuleChecker moduleChecker = new ModuleChecker(strings[0]);
            db.userDao().insertModules(moduleChecker);
            return null;
        }
    }

    class CheckState extends AsyncTask<Integer, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... integers) {
            return  db.userDao().SelectState(integers[0]);

        }
        @Override
        protected void onPostExecute(Boolean Complete) {
            if(Complete == true ) {
                btn1.setBackgroundResource(R.drawable.databases_intro_fin);
            }
            else{
                btn1.setBackgroundResource(R.drawable.databases_intro);
            }
        }
    }*/

    public void loadFragment(android.support.v4.app.Fragment fragment){
        // create a fragment manager and FragmentTransaction to begin the transaction and replace the Fragment, backstack is used to ensure back button presses function normally by popping frags off the stack
        android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction().add(fragment, "questionDB").addToBackStack(null);
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        // replace the Base Layout with new Fragment
        transaction.replace(R.id.constraint_layout, fragment);
        // save the changes
        transaction.commit();
    }
    public String[] storeQuestion () {
        String [] questionArray = new String[28];
        questionArray[0]= "To create a statement on" +"\n"+" a Connection object conn, use:";
        questionArray[1] = "Statement statement = conn.createStatement();";
        questionArray[2] = "What is the function of a jdbc driver?";
        questionArray[3] = "translates query to a format that database can read";
        questionArray[4] = "What exception is caught when accessing a database?";
        questionArray[5] = "SQL Exception";
        questionArray[6] = "Why is it best practice to close a connection to a database?";
        questionArray[7] = "To release any resources the connection is holding on to ";
        questionArray[8] = "Which one of these is a type of statement?";
        questionArray[9] = "Prepared statement ";
        questionArray[10] = "Which of these SQL queries inserts data into a table?";
        questionArray[11] = "Insert into TableName Values (2, ‘Bob’)";
        questionArray[12] = "Which of these SQL queries creates a table in database ?";
        questionArray[13] = "Create Table TableName (column1 Datatype, column2 Datatype)";
        questionArray[14] = "Which of these executes a prepared statement? ";
        questionArray[15]= "PreparedStatement Prepstmt=con.prepareStatement(\"insert into TableName(?,?)\");  Prepstmt.setDatatype(column number, value); prepstmt .execute();";
        questionArray[16] = "Which of these executes a normal statement? ";
        questionArray[17] = "statement = conn.createStatement(); statement.execute(\"INSERT INTO DRIVER VALUES (2,’Bob’ );\")";
        questionArray[18] = "What is a result set? ";
        questionArray[19] = "An object that stores data sent back from the database";
        questionArray[20] = "Which type of execution do select querys use";
        questionArray[21] = "executeQuery ";
        questionArray[22] = "Which of these retrieves the string result of the select query? ";
        questionArray[23] = "Resultset.getString(query);";
        questionArray[24] = "Which of these is the correct select query?";
        questionArray[25] = "Select column1, column2 from table";
        questionArray[26] = "To execute a SELECT statement \"select * from Address\" on a Statement object stmt, use";
        questionArray[27] = "stmt.executeQuery(\"select * from Address\");";



        return questionArray;

    }



}

