package com.example.jianshi.mydatabase;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static android.support.constraint.Constraints.TAG;

public class Glossary extends android.support.v4.app.Fragment  {
    View view;
    private SimpleAdapter simpleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.glossary_content, container, false);

        //initialise the listview to display the items
        ListView listView = view.findViewById(R.id.listView);
        //two dimensional array that contains terms and definitions
        String [][] definitions  = {
                {" java.sql.Connection", " establish a connection with a database"},
                {" java.sql.Statement", " send SQL statements"},
                {" java.sql.Resultset", " process the results"},
                {" CREATE", " Creates a new table, a view of a table, or other object"},
                {" ALTER", " Modifies an existing database object, such as a table"},
                {" DROP", " Deletes entire table, a view of a table or other object"},
                {" INSERT", " Creates a record"},
                {" UPDATE", " Modifies records"},
                {" DELETE", " Deletes records"},
                {" SELECT", " Retrieves certain records from one or more tables"}
        };
        //use of arraylist - hashmap of type string,string to store the two things we want to display.
        //only 0, and 1 are used for the second dimension as only two things are displayed
        ArrayList<HashMap<String,String>> itemlist = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> sorter;
        for(int i=0; i<definitions.length; i++){
            sorter = new HashMap<String,String>();
            sorter.put( "terms", definitions[i][0]);
            sorter.put( "definitions", definitions[i][1]);
            itemlist.add( sorter );
        }
        Log.i(TAG, "arraylist filled");
        //setting the simpleadapter up and binding it to the listview
        simpleAdapter = new SimpleAdapter(getActivity(), itemlist,
                R.layout.list_view_text_box,
                new String[] { "terms","definitions" },
                new int[] {R.id.terms, R.id.definitions});
        listView.setAdapter(simpleAdapter);
        Log.i(TAG, "adapter set");

        return view;
    }
}