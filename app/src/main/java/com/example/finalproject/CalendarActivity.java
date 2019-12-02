package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CalendarView;

import com.google.android.material.navigation.NavigationView;

public class CalendarActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;


    private CalendarView calendarViewTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarViewTV =findViewById(R.id.calendarView);

        //Build in methods that sends the selected date to the activity
        calendarViewTV.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Intent intent = new Intent(CalendarActivity.this,MainActivity.class);
                intent.putExtra("date",dayOfMonth);
                intent.putExtra("month",month +1 );
                intent.putExtra("year",year);
                startActivity(intent);
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
                Intent intent = new Intent(CalendarActivity.this,CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_note:
                Intent intent2 = new Intent(CalendarActivity.this,MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_settings:
                Intent intent3 = new Intent(CalendarActivity.this,settingsActivity.class);
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
