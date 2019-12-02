package com.example.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Adapter.HmwrkRecyclerViewAdapter;
import com.example.finalproject.Adapter.TaskRecyclerViewAdapter;
import com.example.finalproject.Data.DatabaseHandler;
import com.example.finalproject.Model.Homework;
import com.example.finalproject.Model.Task;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class NoteFragment extends Fragment {


    private TextView theDate;
    private LinearLayout addTaskBtn;
    private LinearLayout addHmwrkBtn;

    private Toolbar toolbar;
    private DrawerLayout drawer;

    private RecyclerView recyclerViewTask;
    private RecyclerView recyclerViewHmwrk;
    private TaskRecyclerViewAdapter taskRecyclerViewAdapter;
    private HmwrkRecyclerViewAdapter hmwrkRecyclerViewAdapter;

    private int date;
    private int month;
    private int year;
    private int item_position;

    String message_addtask;
    String message_addhmwrk;

    private  List<Task> taskList;
    private  List<Homework> hmwrkList;

    private DatabaseHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v = inflater.inflate(R.layout.fragment_note, container, false);

        theDate = v.findViewById(R.id.date);
        addTaskBtn = v.findViewById(R.id.addTaskButton);
        addHmwrkBtn = v.findViewById(R.id.addHmwrkButton);
        toolbar = v.findViewById(R.id.toolBar);

        date=getArguments().getInt("date",0);
        month=getArguments().getInt("month",0);
        year=getArguments().getInt("year",0);

    /*    message_addhmwrk=getArguments().getString("message_add_hmwrk");
        message_addtask=getArguments().getString("message_add_task");

        if(message_addtask !=null)
        {
            Toast toast=Toast. makeText(getActivity(),message_addtask,Toast. LENGTH_SHORT);
            toast.show();
        }

        if(message_addhmwrk !=null)
        {
            Toast toast=Toast. makeText(getActivity(),message_addhmwrk,Toast. LENGTH_SHORT);
            toast.show();
        }*/

        setDateText();
        addTaskBttnClick();
        setAddHmwrkBtnClick();
        taskRecycleViewLogic(v);
        setHmwrkRecyclerViewLogic(v);

        return v;
    }

    //Method to Load the Homework RecyclerView
    public void setHmwrkRecyclerViewLogic(View v)
    {
        db = new DatabaseHandler(getActivity());
        hmwrkList =db.getAllHmwrkforDate(date,month,year);

        recyclerViewHmwrk = v.findViewById(R.id.hmwrkRecycleView);
        recyclerViewHmwrk.setHasFixedSize(true);
        recyclerViewHmwrk.setLayoutManager(new LinearLayoutManager(getActivity()));
        hmwrkRecyclerViewAdapter = new HmwrkRecyclerViewAdapter(getActivity(), hmwrkList);
        recyclerViewHmwrk.setAdapter(hmwrkRecyclerViewAdapter);

        hmwrkRecyclerViewAdapter.setOnItemClickListener2(new HmwrkRecyclerViewAdapter.onItemClickListener2() {
            @Override
            public void onDelete(int position) {
                db.deleteHomework(hmwrkList.get(position).getId());
                Handler handler = new Handler();
                item_position = position;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hmwrkList.remove(item_position);
                        recyclerViewHmwrk.removeViewAt(item_position);
                    }
                }, 500);
                hmwrkRecyclerViewAdapter.notifyDataSetChanged();
                // recyclerViewTask.removeViewAt(position);
                hmwrkRecyclerViewAdapter.notifyItemRemoved(position);
                hmwrkRecyclerViewAdapter.notifyItemRangeChanged(position,hmwrkList.size());
            }

            @Override
            public void longClick(int position) {
                Intent intent = new Intent(getActivity(), addHmwrkActivity.class);
                intent.putExtra("name",hmwrkList.get(position).getHwrkName());
                intent.putExtra("desc",hmwrkList.get(position).getHmwkDescirption());
                intent.putExtra("class",hmwrkList.get(position).getHmwkClass());
                intent.putExtra("time",hmwrkList.get(position).getHmwrkTime());
                intent.putExtra("day",hmwrkList.get(position).getDay());
                intent.putExtra("month",hmwrkList.get(position).getMonth());
                intent.putExtra("year",hmwrkList.get(position).getYear());
                intent.putExtra("id",hmwrkList.get(position).getId());
                startActivity(intent);
            }
        });
    }
    //Method to Load the task RecyclerView
    public void taskRecycleViewLogic(View v)
    {
        db = new DatabaseHandler(getActivity());

        taskList = db.getAllTaskforDate(date, month, year);

        recyclerViewTask =v.findViewById(R.id.taskRecycleView);
        recyclerViewTask.setHasFixedSize(true);
        recyclerViewTask.setLayoutManager(new LinearLayoutManager(getActivity()));
        taskRecyclerViewAdapter = new TaskRecyclerViewAdapter(getActivity(),taskList);
        recyclerViewTask.setAdapter(taskRecyclerViewAdapter);

        taskRecyclerViewAdapter.setOnItemClickListener(new TaskRecyclerViewAdapter.onItemClickListener()
        {
            @Override
            public void onDelete(int position)
            {
                db.deleteTask(taskList.get(position).getTaskId());
                Handler handler = new Handler();
                item_position = position;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        taskList.remove(item_position);
                        recyclerViewTask.removeViewAt(item_position);
                    }
                }, 500);
                taskRecyclerViewAdapter.notifyDataSetChanged();
                // recyclerViewTask.removeViewAt(position);
                taskRecyclerViewAdapter.notifyItemRemoved(position);
                taskRecyclerViewAdapter.notifyItemRangeChanged(position,taskList.size());
            }

            @Override
            public void longClick(int position) {
                Intent intent = new Intent(getActivity(), addTaskActivity.class);
                intent.putExtra("name",taskList.get(position).getTaskName());
                intent.putExtra("desc",taskList.get(position).getTaskDescription());
                intent.putExtra("time",taskList.get(position).getTaskTime());
                intent.putExtra("day",taskList.get(position).getDay());
                intent.putExtra("month",taskList.get(position).getMonth());
                intent.putExtra("year",taskList.get(position).getYear());
                intent.putExtra("id",taskList.get(position).getTaskId());
                startActivity(intent);
            }
        });
    }
    //Logic when add task View is clicked
    public void addTaskBttnClick()
    {
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), addTaskActivity.class);
                intent.putExtra("day", date);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                startActivity(intent);
            }
        });
    }
    //Logic when add Homework View is Clicked
    public void setAddHmwrkBtnClick()
    {
        addHmwrkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), addHmwrkActivity.class);
                intent.putExtra("day", date);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                startActivity(intent);
            }
        });
    }
    //Method to set theDate textview text
    public void setDateText()
    {
        theDate.setText(year + "/"+month + "/" +date);
    }
}


