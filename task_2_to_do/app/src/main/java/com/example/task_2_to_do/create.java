package com.example.task_2_to_do;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class create extends AppCompatActivity {

    Button b;
    EditText ed1;
    TextView ed2;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        b=findViewById(R.id.add);
        ed1=findViewById(R.id.task);
        ed2=findViewById(R.id.date);
        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        create.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                ed2.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });
        dbHelper=new DBHelper(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String task=ed1.getText().toString();
                String date=ed2.getText().toString();
                if (task.isEmpty() || date.isEmpty()){
                    Toast.makeText(create.this, "Please enter the fields", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Boolean check=dbHelper.insert(task,date);
                    if (check){
                        Toast.makeText(create.this, "Remainder added successfully", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(create.this, "Internal Error", Toast.LENGTH_LONG).show();
                    }
                    Intent intent = new Intent(create.this, main_page.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(create.this, main_page.class));
        finish();

    }
}