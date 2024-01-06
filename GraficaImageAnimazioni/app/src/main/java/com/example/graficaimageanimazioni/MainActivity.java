package com.example.graficaimageanimazioni;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Animation[] animations = new Animation[5];
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.globo);

        for (int i=0; i<5; i++) {
            String nome = "button"+(i+1);
            Button b = (Button) findViewById(getResources().
                    getIdentifier(nome,"id",getPackageName()));
            b.setOnClickListener(this);

            String file = "animazione"+(i+1);
            animations[i]= AnimationUtils.loadAnimation(getApplicationContext(),
                    getResources().getIdentifier(file,
                            "anim",getPackageName()));
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.button1) {
            img.startAnimation(animations[0]);
        } else if (id == R.id.button2) {
            img.startAnimation(animations[1]);
        } else if (id == R.id.button3) {
            img.startAnimation(animations[2]);
        } else if (id == R.id.button4) {
            img.startAnimation(animations[3]);
        } else if (id == R.id.button5) {
            img.startAnimation(animations[4]);
        } else {
            Log.d("DEBUG", "Oopppss...");
        }
    }

}
