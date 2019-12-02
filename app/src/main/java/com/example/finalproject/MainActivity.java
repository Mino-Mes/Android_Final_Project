package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finalproject.Adapter.HmwrkRecyclerViewAdapter;
import com.example.finalproject.Adapter.TaskRecyclerViewAdapter;
import com.example.finalproject.Data.DatabaseHandler;
import com.example.finalproject.Model.Homework;
import com.example.finalproject.Model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

   private Toolbar toolbar;
   private DrawerLayout drawer;

    private Date todayDate;
    private SimpleDateFormat formatter_year;
    private SimpleDateFormat formatter_month;
    private SimpleDateFormat formatter_date;

    int date;
    int month;
    int year;
    String message_addtask;
    String message_addhmwrk;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Logic to get the the Current day,Month, and Year in Numbers
        todayDate = Calendar.getInstance().getTime();

        formatter_year= new SimpleDateFormat("yyyy");
        String todayYear =formatter_year.format(todayDate);

        Log.d("year", "onCreateView:asd " + todayYear);

        formatter_month= new SimpleDateFormat("MM");
        String todayMonth =formatter_month.format(todayDate);

        formatter_date= new SimpleDateFormat("dd");
        String todayDay =formatter_date.format(todayDate);

        //Receive the Incoming date values from the other activities
        Intent incomingIntent = getIntent();
        date = incomingIntent.getIntExtra("date", Integer.parseInt(todayDay));
        month = incomingIntent.getIntExtra("month", Integer.parseInt(todayMonth));
        year = incomingIntent.getIntExtra("year", Integer.parseInt(todayYear));

    /*    if(getIntent() !=null)
        {
            message_addtask = incomingIntent.getStringExtra("message_AddTask");
            message_addhmwrk = incomingIntent.getStringExtra("message_AddHomework");
        }*/

    //Load the note Fragment and pass the date values
       NoteFragment fragment = new NoteFragment();
       getSupportFragmentManager().beginTransaction().replace(R.id.noteFragmentContainer,fragment).commit();
       Bundle bundle = new Bundle();
       bundle.putInt("date",date);
    //   bundle.putString("add_task_Message",message_addtask);
    //   bundle.putString("add_hmrk_message",message_addhmwrk);
       bundle.putInt("month",month);
       bundle.putInt("year",year);
       fragment.setArguments(bundle);

       //The toolbar and Navigation View initializaiton
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.draw_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
    }

    //The Navigation switch statement that takes the user to another acitivty on click
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        switch(Item.getItemId())
        {
            case R.id.nav_calendar:
                Log.d("item", "onNavigationItemSelected:CLICKED ");
                Intent intent = new Intent(MainActivity.this,CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_note:
                Intent intent2 = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_settings:
                Intent intent3 = new Intent(MainActivity.this,settingsActivity.class);
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
