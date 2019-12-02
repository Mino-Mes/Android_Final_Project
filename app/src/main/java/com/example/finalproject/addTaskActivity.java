package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.Data.DatabaseHandler;
import com.example.finalproject.Model.Task;

public class addTaskActivity extends AppCompatActivity {

    private EditText nameBox;
    private EditText descBox;
    private EditText timeBox;
    private Button submitBttn;

    int date;
    int month;
    int year;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        nameBox = findViewById(R.id.nameBx);
        descBox = findViewById(R.id.descriptionBox);
        timeBox = findViewById(R.id.timeBox);

        submitBttn = findViewById(R.id.submitButton);

        Intent incomingIntent = getIntent();
        date = incomingIntent.getIntExtra("day", 0);
        month = incomingIntent.getIntExtra("month", 0);
        year = incomingIntent.getIntExtra("year", 0);

        String name = incomingIntent.getStringExtra("name");
        String desc = incomingIntent.getStringExtra("desc");
        String time = incomingIntent.getStringExtra("time");
        id = incomingIntent.getIntExtra("id",0);

        //If the user decides to Update
         if (name != null || desc != null || time !=null && id !=0) {
            nameBox.setText(name);
            descBox.setText(desc);
            timeBox.setText(time);

            submitBttn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("test", "onCreate: update" + descBox.getText().toString());
                    DatabaseHandler db = new DatabaseHandler(addTaskActivity.this);

                    db.updateTask(id,nameBox.getText().toString(),descBox.getText().toString(),timeBox.getText().toString());

                    Intent intent = new Intent(addTaskActivity.this, MainActivity.class);
                  //  intent.putExtra("message_updateTask", "Task has been updated");
                    intent.putExtra("date", date);
                    intent.putExtra("month", month);
                    intent.putExtra("year", year);

                    startActivity(intent);
                }
            });
            //TO add a Task
        }else {
            submitBttn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(nameBox.getText().toString().matches("") ||descBox.getText().toString().matches("") || timeBox.getText().toString().matches(""))
                    {
                        Toast toast=Toast. makeText(getApplicationContext(),"All fields must be filled, Thank you !",Toast. LENGTH_SHORT);
                        toast.show();
                    }else
                    {
                        Task task = new Task(nameBox.getText().toString(), descBox.getText().toString(), timeBox.getText().toString(), date, month, year);

                        DatabaseHandler db = new DatabaseHandler(addTaskActivity.this);
                        db.addTask(task);

                        Intent intent = new Intent(addTaskActivity.this, MainActivity.class);
                        intent.putExtra("message_AddTask", "Task has been added");
                        intent.putExtra("date", date);
                        intent.putExtra("month", month);
                        intent.putExtra("year", year);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
