package com.example.task_3_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int c=0;
    double tim=0;
    TextView t1, t2;
    Boolean bol = false;
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, clear, backsp, equal, plus, minus, mul, div, power, dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b0 = findViewById(R.id.button_zero);
        b1 = findViewById(R.id.button_one);
        b2 = findViewById(R.id.button_two);
        b3 = findViewById(R.id.button_three);
        b4 = findViewById(R.id.button_four);
        b5 = findViewById(R.id.button_five);
        b6 = findViewById(R.id.button_six);
        b7 = findViewById(R.id.button_seven);
        b8 = findViewById(R.id.button_eight);
        b9 = findViewById(R.id.button_nine);
        plus = findViewById(R.id.button_addition);
        minus = findViewById(R.id.button_subtraction);
        mul = findViewById(R.id.button_multiplication);
        div = findViewById(R.id.button_division);
        clear = findViewById(R.id.button_clear);
        backsp = findViewById(R.id.button_backspace);
        equal = findViewById(R.id.button_equal);
        power = findViewById(R.id.button_pow);
        dot = findViewById(R.id.button_dot);
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        div.setOnClickListener(this);
        mul.setOnClickListener(this);
        clear.setOnClickListener(this);
        backsp.setOnClickListener(this);
        equal.setOnClickListener(this);
        power.setOnClickListener(this);
        t2 = findViewById(R.id.history);
        t1 = findViewById(R.id.input);
        t2.setMovementMethod(new ScrollingMovementMethod());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        String m = t1.getText().toString();
        if (m.equals("0")) {
//            Toast.makeText(this, "activated", Toast.LENGTH_SHORT).show();
            m = "";
        }
        if (bol) {
            bol = false;
            m="";
        }
        switch (view.getId()) {
            case R.id.button_zero:
                t1.setText(m + "0");
                break;
            case R.id.button_one:
                t1.setText(m + "1");
                break;
            case R.id.button_two:
                t1.setText(m + "2");
                break;
            case R.id.button_three:
                t1.setText(m + "3");
                break;
            case R.id.button_four:
                t1.setText(m + "4");
                break;
            case R.id.button_five:
                t1.setText(m + "5");
                break;
            case R.id.button_six:
                t1.setText(m + "6");
                break;
            case R.id.button_seven:
                t1.setText(m + "7");
                break;
            case R.id.button_eight:
                t1.setText(m + "8");
                break;
            case R.id.button_nine:
                t1.setText(m + "9");
                break;
            case R.id.button_dot:
                t1.setText(m + ".");
                break;
            case R.id.button_pow:
                t1.setText(m + "^");
                break;
            case R.id.button_addition:
                t1.setText(m + "+");
                break;
            case R.id.button_multiplication:
                t1.setText(m + "*");
                break;
            case R.id.button_division:
                t1.setText(m + "/");
                break;
            case R.id.button_subtraction:
                t1.setText(m + "-");
                break;
            case R.id.button_clear:
                c++;
                if (c==1)
                    tim=Calendar.getInstance().getTimeInMillis();
                t1.setText("0");
//                Toast.makeText(this, ""+(double)Calendar.getInstance().getTimeInMillis()+"  "+tim, Toast.LENGTH_SHORT).show();
                if (c>=2 && (double)Calendar.getInstance().getTimeInMillis()-tim<=1000){
                    t2.setText("History");
                    c=0;
                }
                if (c>2)
                    c=0;
                break;
            case R.id.button_backspace:
                try {
                    String m1 = m;
                    m1 = m1.substring(0, m.length() - 1);
                    t1.setText(m1);
                } catch (Exception e) {
                }
                break;
            case R.id.button_equal:
                if (verify(m)) {
                    Object res = eval(m);
                    if (res == null) {
                        t1.setText("0");
                    } else {
                        t1.setText("" + res);
                        t2.setText(t2.getText() + "\n" + m + "  \n=   " + res + "\n");

                    }
                } else {
                    Toast.makeText(this, "Invalid Expression", Toast.LENGTH_SHORT).show();
                }
                bol = true;
                break;
        }
    }

//    private void  dup(String s) {
//        String m=t1.getText().toString();
//        if ( m.endsWith("+") || m.endsWith("-") || m.endsWith("*") || m.endsWith("/") || m.endsWith("^") || m.endsWith(".")){
//            m.replace(m.charAt(m.length()-1),s.charAt(0));
//            t1.setText(m);
//        }
//    }

    public Boolean verify(String m) {
        if (m.length() < 3 || m.endsWith("+") || m.endsWith("-") || m.endsWith("*") || m.endsWith("/") || m.endsWith("^"))
            return false;
        return true;
    }

    private Object eval(String s) {
        s=s.trim();
        s=s.replaceAll("\\^","**");
//        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        Object result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        try {
            result = engine.eval(s);
//            Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Invalid  Expression", Toast.LENGTH_LONG).show();
        }

        return result;
//        return -1;
    }
}