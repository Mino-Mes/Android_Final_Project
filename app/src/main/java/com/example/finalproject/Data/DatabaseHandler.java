package com.example.finalproject.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.finalproject.Model.Homework;
import com.example.finalproject.Model.Task;
import com.example.finalproject.R;
import com.example.finalproject.Util.database_Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
   public DatabaseHandler(Context context){
       super(context, database_Util.DATABASE_NAME,null,database_Util.DATABASE_VERSION);
   }

   // CREATE TABLE NOTE, TASK AND HOMEWORK
    @Override
    public void onCreate(SQLiteDatabase db) {
       String CREATE_TASK_TABLE ="CREATE TABLE " + database_Util.TASK_TABLE + "("
                                 + database_Util.KEY_TASK_ID + " INTEGER PRIMARY KEY,"
                                 + database_Util.KEY_TASK_NAME + " TEXT,"
                                 + database_Util.KEY_TASK_DESC + " TEXT,"
                                 + database_Util.KEY_TASK_TIME + " TEXT,"
                                 + database_Util.KEY_TASK_DAY  + " INTEGER,"
                                 + database_Util.KEY_TASK_MONTH  + " INTEGER,"
                                 + database_Util.KEY_TASK_YEAR  + " INTEGER,"
                                 + database_Util.KEY_TASK_IS_DONE + " INTEGER" + ")";

        String CREATE_HMWRK_TABLE ="CREATE TABLE " + database_Util.HMWRK_TABLE + "("
                                + database_Util.KEY_HMWRK_ID + " INTEGER PRIMARY KEY,"
                                + database_Util.KEY_HMWRK_NAME + " TEXT,"
                                + database_Util.KEY_HMWRK_DESC + " TEXT,"
                                + database_Util.KEY_HMWRK_CLASS + " TEXT,"
                                + database_Util.KEY_HMWRK_TIME + " TEXT,"
                                + database_Util.KEY_HMWRK_DAY  + " INTEGER,"
                                + database_Util.KEY_HMWRK_MONTH  + " INTEGER,"
                                + database_Util.KEY_HMWRK_YEAR  + " INTEGER" + ")";

       db.execSQL(CREATE_TASK_TABLE);
       db.execSQL(CREATE_HMWRK_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.dp_drop);
        db.execSQL(DROP_TABLE, new String[]{database_Util.DATABASE_NAME});

        onCreate(db);
    }

    //Add a Task
    public void addTask(Task task)
    {
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(database_Util.KEY_TASK_NAME, task.getTaskName());
        values.put(database_Util.KEY_TASK_DESC,task.getTaskDescription());
        values.put(database_Util.KEY_TASK_TIME,task.getTaskTime());
        values.put(database_Util.KEY_TASK_DAY,task.getDay());
        values.put(database_Util.KEY_TASK_MONTH,task.getMonth());
        values.put(database_Util.KEY_TASK_YEAR,task.getYear());

        db.insert(database_Util.TASK_TABLE,null,values);
        db.close();
    }
    //Get a Task
    public Task getTask(int id)
    {
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor =db.query(database_Util.TASK_TABLE,new String[]{database_Util.KEY_TASK_ID,database_Util.KEY_TASK_NAME,database_Util.KEY_TASK_DESC,database_Util.KEY_TASK_TIME,database_Util.KEY_TASK_DAY,database_Util.KEY_TASK_MONTH,database_Util.KEY_TASK_YEAR},
                database_Util.KEY_TASK_ID +"=?", new String[]{String.valueOf(id)},null,null,null);
        if(cursor !=null)
        {
            cursor.moveToFirst();

            Task task = new Task(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6));

            return task;
        }
        else
        {
            db.close();
            return null;
        }
    }
    //Get All task For a specific Date
    public List<Task> getAllTaskforDate(int day, int month, int year){
        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        //SELECT * FROM TASK WHERE task_day = AND task_month = AND task_year =
       String query = "SELECT * FROM task WHERE task_day =" + day + " AND task_month =" + month + " AND task_year ="+ year ;
     //String query = "SELECT * FROM " + database_Util.TASK_TABLE;
        Cursor cursor =db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do{
                Task task = new Task(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6));

                taskList.add(task);
            }while(cursor.moveToNext());
        }
        return taskList;
    }

    //Update a Task
    public int updateTask(int id, String name, String description, String time)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(database_Util.KEY_TASK_NAME,name);
        values.put(database_Util.KEY_TASK_DESC,description);
        values.put(database_Util.KEY_TASK_TIME,time);

        return db.update(database_Util.TASK_TABLE,values,database_Util.KEY_TASK_ID+"=?", new String[]{String.valueOf(id)});
    }

    //Delete a Task
    public void deleteTask(int id)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        String query="DELETE FROM task WHERE " + database_Util.KEY_TASK_ID + " = " + id;

        db.execSQL(query);
    }

    //Add a Homework
    public void addHomework(Homework hmwrk)
    {
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(database_Util.KEY_HMWRK_NAME, hmwrk.getHwrkName());
        values.put(database_Util.KEY_HMWRK_DESC,hmwrk.getHmwkDescirption());
        values.put(database_Util.KEY_HMWRK_CLASS,hmwrk.getHmwkClass());
        values.put(database_Util.KEY_HMWRK_TIME,hmwrk.getHmwrkTime());
        values.put(database_Util.KEY_HMWRK_DAY,hmwrk.getDay());
        values.put(database_Util.KEY_HMWRK_MONTH,hmwrk.getMonth());
        values.put(database_Util.KEY_HMWRK_YEAR,hmwrk.getYear());

        db.insert(database_Util.HMWRK_TABLE,null,values);
        db.close();
    }


    //Get All homework For a specific Date
    public List<Homework> getAllHmwrkforDate(int day, int month, int year){
        List<Homework> hmwrkList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        //SELECT * FROM HOMEWORK WHERE task_day = AND task_month = AND task_year =
        String query = "SELECT * FROM homework WHERE hmwrk_day =" + day + " AND hmwrk_month =" + month + " AND hmwrk_year ="+ year ;

        Cursor cursor =db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do{
                Homework hmwrk = new Homework(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7));

                hmwrkList.add(hmwrk);
            }while(cursor.moveToNext());
        }
        return hmwrkList;
    }

    //Update a Homework
    public int updateHomework(int id, String name, String description,String className, String time)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(database_Util.KEY_HMWRK_NAME,name);
        values.put(database_Util.KEY_HMWRK_DESC,description);
        values.put(database_Util.KEY_HMWRK_TIME,time);
        values.put(database_Util.KEY_HMWRK_CLASS,className);

        return db.update(database_Util.HMWRK_TABLE,values,database_Util.KEY_HMWRK_ID+"=?", new String[]{String.valueOf(id)});
    }

    //Delete a Homework
    public void deleteHomework(int id)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        String query="DELETE FROM homework WHERE " + database_Util.KEY_HMWRK_ID + " = " + id;

        db.execSQL(query);
    }

    //Delete All Homework
    public void deleteHomework()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        String query="DELETE FROM homework";

        db.execSQL(query);
    }

    //Delete All Task
    public void deleteTask()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        String query="DELETE FROM task";

        db.execSQL(query);
    }
}
