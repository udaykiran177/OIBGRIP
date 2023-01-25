package com.example.task_1_unit_convertor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.length);
        b2=findViewById(R.id.weight);
        b3=findViewById(R.id.temperature);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }

    public void length(){
        Intent intent=new Intent(MainActivity.this,length_conv.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.length:
                intent=new Intent(MainActivity.this,length_conv.class);
                break;
            case R.id.temperature:
                intent=new Intent(MainActivity.this,temp_conv.class);
                break;
            case R.id.weight:
                intent=new Intent(MainActivity.this,weight_conv.class);
        }
        startActivity(intent);
    }
}