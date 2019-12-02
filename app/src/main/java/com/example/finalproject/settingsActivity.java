package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.finalproject.Data.DatabaseHandler;
import com.example.finalproject.Util.Prefs;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class settingsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;

    private RadioButton rbEnglish;
    private RadioButton rbFrench;
    private RadioGroup rgLang;
    private Button submitButton;
    private Button deleteAll;

    private Prefs preferences;
    private String check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        rbEnglish=findViewById(R.id.englishRadio);
        rbFrench=findViewById(R.id.frenchRadio);
        rgLang=findViewById(R.id.radioGroup);
        submitButton=findViewById(R.id.saveButton);
        deleteAll=findViewById(R.id.deleteAll);

        preferences =new Prefs(settingsActivity.this);
        check =preferences.getCheck();

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(settingsActivity.this);

                db.deleteHomework();
                db.deleteTask();
                Toast toast=Toast.makeText(settingsActivity.this,"All Tasks and Homework were Deleted",Toast. LENGTH_SHORT);
                toast.show();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                DisplayMetrics ds= res.getDisplayMetrics();
                Configuration conf=res.getConfiguration();

                if(rbEnglish.isChecked())
                {
                    preferences.saveRadioCheck("en");
                }

                if(rbFrench.isChecked())
                {
                    preferences.saveRadioCheck("fr");
                }

                if(check == "en")
                {
                    conf.setLocale(new Locale("en".toLowerCase()));
                }

                if(check=="fr")
                {
                    conf.setLocale(new Locale("en".toLowerCase()));
                }
            }
        });

        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.draw_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        switch(Item.getItemId())
        {
            case R.id.nav_calendar:
                Log.d("item", "onNavigationItemSelected:CLICKED ");
                Intent intent = new Intent(settingsActivity.this,CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_note:
                Intent intent2 = new Intent(settingsActivity.this,MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_settings:
                Intent intent3 = new Intent(settingsActivity.this,settingsActivity.class);
                startActivity(intent3);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}
