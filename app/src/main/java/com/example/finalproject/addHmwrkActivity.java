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
import com.example.finalproject.Model.Homework;
import com.example.finalproject.Model.Task;

public class addHmwrkActivity extends AppCompatActivity {
    private EditText nameBox;
    private EditText descBox;
    private EditText timeBox;
    private EditText classBox;
    private Button submitBttn;

    int date;
    int month;
    int year;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hmwrk);
        nameBox=findViewById(R.id.nameBx);
        descBox=findViewById(R.id.descriptionBox);
        timeBox=findViewById(R.id.timeBox);
        classBox=findViewById(R.id.classBx);

        submitBttn=findViewById(R.id.submitButtonH);

        Intent incomingIntent= getIntent();
        date = incomingIntent.getIntExtra("day", 0);
        month = incomingIntent.getIntExtra("month", 0);
        year = incomingIntent.getIntExtra("year", 0);

        String name = incomingIntent.getStringExtra("name");
        String desc = incomingIntent.getStringExtra("desc");
        String time = incomingIntent.getStringExtra("time");
        String className=incomingIntent.getStringExtra("class");
        id = incomingIntent.getIntExtra("id",0);

        Log.d("hmwrk", "onClick: " + date );

        //Update a Homework
        if(name!=null||desc!=null||time !=null || className !=null || id !=0)
        {
            nameBox.setText(name);
            descBox.setText(desc);
            timeBox.setText(time);
            classBox.setText(className);

            submitBttn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("test", "onCreate: update" + descBox.getText().toString());
                    DatabaseHandler db = new DatabaseHandler(addHmwrkActivity.this);

                    db.updateHomework(id,nameBox.getText().toString(),descBox.getText().toString(),classBox.getText().toString(),timeBox.getText().toString());

                    Intent intent = new Intent(addHmwrkActivity.this, MainActivity.class);
                  //  intent.putExtra("message_updateHmwrk", "Hmwrk has been updated");
                    intent.putExtra("date", date);
                    intent.putExtra("month", month);
                    intent.putExtra("year", year);

                    startActivity(intent);
                }
            });
            //Add a homework
        }else{
            submitBttn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(nameBox.getText().toString().matches("") ||descBox.getText().toString().matches("") || timeBox.getText().toString().matches("") || classBox.getText().toString().matches(""))
                    {
                        Toast toast=Toast. makeText(getApplicationContext(),"All fields must be filled, Thank you !",Toast. LENGTH_SHORT);
                        toast.show();
                    }else
                    {
                        Homework hmwrk = new Homework(nameBox.getText().toString(), descBox.getText().toString(),classBox.getText().toString() ,timeBox.getText().toString(), date, month, year);

                        DatabaseHandler db = new DatabaseHandler(addHmwrkActivity.this);
                        db.addHomework(hmwrk);

                        Intent intent = new Intent(addHmwrkActivity.this, MainActivity.class);
                        intent.putExtra("message_AddHomework", "Homework has been added");
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
