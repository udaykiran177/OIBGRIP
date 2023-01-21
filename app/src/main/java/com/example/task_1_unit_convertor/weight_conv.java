package com.example.task_1_unit_convertor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class weight_conv extends AppCompatActivity implements View.OnClickListener {
    RadioGroup radioGroup1,radioGroup2;
    RadioButton r1=null,r2=null;
    private EditText e1;
    TextView e2;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_conv);
        radioGroup1 = findViewById(R.id.radio);
        b=findViewById(R.id.conv);
        radioGroup2 = findViewById(R.id.radio1);
        e2=findViewById(R.id.unit_1);
        e1=findViewById(R.id.unit_2);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                r1= findViewById(checkedId);
            }
        });
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                r2= findViewById(checkedId);
            }
        });
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (r1==null || r2==null || e1.getText().toString().length()==0){
            Toast.makeText(this,"Please Provide Units",Toast.LENGTH_LONG).show();
        }
        else{
            String m=r1.getText().toString();
            String n=r2.getText().toString();
            long val;
            val=Integer.parseInt(e1.getText().toString());

                //converting every unit to cm

                double res=0;
                switch (m){
                    case "Gram":
                        res=this.conv(val);
                        break;
                    case "Kilogram":
                        res=this.conv(val*1000);
                        break;
                    case "Tonne":
                        res=this.conv(val*Math.pow(10,6));
//                        Toast.makeText(this,""+val,Toast.LENGTH_LONG).show();
                        break;
                }
                e2.setText(res+" "+n);

        }
    }

    private double conv(double val) {
        String n=r2.getText().toString();
        //converting the cm unit to respective unit

        switch(n){
            case "Gram":
                return val;
            case "Kilogram":
                return val/1000;
            case "Tonne":
//                Toast.makeText(this,""+val*(1/274.15),Toast.LENGTH_LONG).show();
                return val/Math.pow(10,6);
        }
        return -1;
    }
}