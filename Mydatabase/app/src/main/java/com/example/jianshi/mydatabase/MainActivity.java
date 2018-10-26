package com.example.jianshi.mydatabase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static android.support.constraint.Constraints.TAG;

//navigationview is used as the navigationbar in our app
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

 public TextView welcome;
 public Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setting up the navigation and toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Log.i(TAG, "navigation bar initialised");
        //setting the welcome text and button
        welcome = findViewById(R.id.Welcome);
        start = findViewById(R.id.started);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcome.setVisibility(View.GONE);
                start.setVisibility(View.GONE);
                loadFragment(new QuestionDatabase());
            }
        });
    }


    //default onbackpressed for navigationbar
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //set the image at the top of the navigationbar
        ImageView image = (ImageView)findViewById( R.id.imageView);
        image.setImageResource(R.drawable.picturedb);
        return true;
    }
    //default optionsselected of navbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //navigation view item clicks are routed to different fragments
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        welcome.setVisibility(View.INVISIBLE);
        start.setVisibility(View.GONE);
        if (id == R.id.nav_home) {
            // Handle the home action
            Log.i(TAG, "home selected");
            loadFragment(new QuestionDatabase());
        } else if (id == R.id.nav_glossary) {
            //handle the glossary action
            Log.i(TAG, "glossary selected");
            loadFragment(new Glossary());
        } else if (id == R.id.nav_help) {
            //handle the help action
            Log.i(TAG, "help selected");
            loadFragment(new Help());
        } else if (id == R.id.nav_videos) {
            //handle the video action
            Log.i(TAG, "videos selected");
            loadFragment(new Video());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void loadFragment(android.support.v4.app.Fragment fragment){
        // create a fragment manager and FragmentTransaction to begin the transaction and replace the Fragment, backstack is used to ensure back button presses function normally by popping frags off the stack
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().add(fragment, "detail").addToBackStack(null);
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        // replace the Base Layout with new Fragment
        transaction.replace(R.id.constraint_layout, fragment);
        // save the changes
        transaction.commit();
    }







}
