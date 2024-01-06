package com.example.listeners;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rl = findViewById(R.id.rl);
        rl.setOnTouchListener(this);

        Button pulsante1 = findViewById(R.id.pulsante1);
        // pulsante1.setOnClickListener(this);
        pulsante1.setOnTouchListener(this);
        Button pulsante2 = findViewById(R.id.pulsante2);
        pulsante2.setOnTouchListener(this);

    }

    /*

    //implements View.OnClickListener
    @Override
    public void onClick(View v)
    {
        Button b = (Button) v;
        int id = v.getId();
        String testo = b.getText().toString();

        Log.d("DEBUG", "myClick: Click su view: " + testo);
    } */

    @Override
    public boolean onTouch(View v, MotionEvent e)
    {
        int x = (int) e.getX();
        int y = (int) e.getY();
        String s = " x="+ x + " y="+y;

        Log.d("DEBUG", "onTouch: touched" + s);
        return true;
    }

    public void myClick(View v)
    {
        Log.d("DEBUG", "myClick: Click!");
    }
}