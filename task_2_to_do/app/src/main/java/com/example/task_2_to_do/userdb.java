package com.example.task_2_to_do;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

public class userdb extends SQLiteOpenHelper {

    public userdb(Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table user(name TEXT primary key,mail TEXT,number TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists user");
    }

    public  boolean onInsert(String name,String mail,String number,String pass){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("mail",mail);
        contentValues.put("number",number);
        contentValues.put("password",pass);
        long result=sqLiteDatabase.insert("user",null,contentValues);
        if (result==-1){
            return false;
        }
        return true;
    }

    public Cursor getData(String us){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from user where name='"+us+"'",null);
//        cursor.moveToFirst();
//        Log.d("Tag show the data",cursor.getString(1));
        return cursor;
    }
}
