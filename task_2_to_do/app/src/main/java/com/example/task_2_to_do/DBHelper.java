package com.example.task_2_to_do;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
public class DBHelper  extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context,"Tasks.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table tasks(task TEXT,time TEXT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists tasks");
    }

    public boolean insert(String task, String time){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("task",task);
        contentValues.put("time",time);
        long result=sqLiteDatabase.insert("tasks",null,contentValues);
        if (result==-1){
            return false;
        }
        return true;
    }

    public Cursor getData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from tasks",null);
        return cursor;
    }

    public Boolean deleteuserdata(String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from tasks where task = ?", new String[]{name});
        if(cursor.getCount()>0)
        {
            long result = DB.delete("tasks", "task= ?", new String[]{name});
            if(result==-1)
            {
                return  false;
            }
            else
            {
                return true;
            }
        }

        return false;


    }
}
