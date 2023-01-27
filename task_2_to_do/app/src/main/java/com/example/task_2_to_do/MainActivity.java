package com.example.task_2_to_do;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button b1,b2;
    EditText ed1,ed2;
    String s1,s2;
    userdb db;
    List<String> user=new ArrayList<>();
    List<String>pass=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.but);
        b2=findViewById(R.id.appCompatButton);
        ed1=findViewById(R.id.username);
        ed2=findViewById(R.id.password);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        s1=getIntent().getStringExtra("name");
        s2=getIntent().getStringExtra("password");
    }

    @Override
    public void onClick(View v) {
        db=new userdb(this);
        int c=0;
        switch (v.getId()) {
//            case R.id.appCompatButton:
//                Intent intent=new Intent(MainActivity.this,signUp.class);
//                startActivity(intent);
//                break;
//            case R.id.but:
//                Intent intent1=new Intent(MainActivity.this,main_page.class);
//                startActivity(intent1);
//                break;
            case R.id.appCompatButton:
                Intent intent = new Intent(MainActivity.this, signUp.class);
                startActivity(intent);
                break;
            case R.id.but:
                String u=ed1.getText().toString();
                String p=ed2.getText().toString();
//                userdb db=new userdb(this);
//                Cursor cursor=db.getData(u);
//                cursor.moveToFirst();
//                while(cursor.isAfterLast()){
//                    user.add(cursor.getString(0)+"  "+cursor.getString(1));
//                    pass.add(cursor.getString(2)+"  "+cursor.getString(3));
//                    cursor.moveToNext();
//                }
//
//                Toast.makeText(this, user+"         "+pass, Toast.LENGTH_SHORT).show();
                if (s1.equals(u) && p.equals(s2)){
                    Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
                    Intent intent1=new Intent(MainActivity.this,main_page.class);
                    startActivity(intent1);
                }
                else{
                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
        }
    }
}