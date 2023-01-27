package com.example.task_2_to_do;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signUp extends AppCompatActivity implements View.OnClickListener{
    Button b;
//    userdb use;
    EditText ed1,ed2,ed3,ed4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        b=findViewById(R.id.reg);
        ed1=findViewById(R.id.user);
        ed2=findViewById(R.id.mail);
        ed3=findViewById(R.id.number);
        ed4=findViewById(R.id.password);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        Intent intent=new Intent(signUp.this,MainActivity.class);
        String s1=ed1.getText().toString();
        String s2=ed2.getText().toString();
        String s3=ed3.getText().toString();
        String s4=ed4.getText().toString();
//        use=new userdb(this);
//        Boolean i=use.onInsert(s1,s2,s3,s4);
        if (!s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty() && !s4.isEmpty()) {
            Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show();
            Intent intent1=new Intent(signUp.this,MainActivity.class);
            intent1.putExtra("name",s1);
            intent1.putExtra("password",s4);
            startActivity(intent1);
        }
        else{
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Alert!");
            builder.setMessage("User Already Exists");
            builder.setPositiveButton("Ok", (DialogInterface.OnClickListener)(dialog, which)->{
                return ;
            });
        }
    }
}