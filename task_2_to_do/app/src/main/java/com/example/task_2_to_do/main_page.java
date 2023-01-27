package com.example.task_2_to_do;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class main_page extends AppCompatActivity{

    Button b;
    DBHelper dbHelper;
    RecyclerView recyclerView;
    ArrayList<String> task,date;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
//        b=findViewById(R.id.add);
//        b.setOnClickListener(this);
        dbHelper=new DBHelper(this);
        task=new ArrayList<String>();
        date=new ArrayList<String>();
        recyclerView=findViewById(R.id.recycle);
        myAdapter=new MyAdapter(this,task,date);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();
    }

    private void displayData() {
        Cursor cursor=dbHelper.getData();
        if (cursor.getCount()==0){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(main_page.this,create.class);
        }
        while (cursor.moveToNext()){
            task.add(cursor.getString(0));
            date.add(cursor.getString(1));
        }
    }


    public void onclick(View v) {
        Intent intent=new Intent(main_page.this,create.class);
        startActivity(intent);
    }

    public void reload() {
        Intent i = new Intent(main_page.this, main_page.class);
        finish();
        overridePendingTransition(0, 0);
        startActivity(i);
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(main_page.this, MainActivity.class));
        finish();

    }
}