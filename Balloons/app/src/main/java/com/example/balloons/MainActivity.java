package com.example.balloons;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FrameLayout frame_container;
    BackImages backImages;
    final Handler handler = new Handler();
    ArrayList <Balloon> balloons = new ArrayList<>();
    int punteggio = 0;
    TextView tvPunteggio;
    Bitmap bbang;
    Bang explosion;
    int timer_explosion;

    final Runnable r = new Runnable(){
        @Override
        public void run()
        {
            frame_container.invalidate();
            backImages.invalidate();
            for (Balloon b : balloons)
            {
                b.invalidate();
            }
            timer_explosion--;
            if (timer_explosion < 0)
            {
                timer_explosion = 10;
                frame_container.removeView(explosion);
            }
            handler.postDelayed(this, 10);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Si ottengono le dimensioni del display
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int wpx = point.x;
        int hpx = point.y;
        /*
        Log.d("DEBUG", "x " + wpx);
        Log.d("DEBUG", "y " + hpx); */

        tvPunteggio = findViewById(R.id.tvPunteggio);
        tvPunteggio.setText("Punteggio: " + punteggio);

        frame_container = findViewById(R.id.frame_container);

        Drawable ground = getApplicationContext().getResources().getDrawable(R.drawable.ground);
        Drawable sky = getApplicationContext().getResources().getDrawable(R.drawable.sky);
        Drawable bang = getApplicationContext().getResources().getDrawable(R.drawable.bang);

        Bitmap bground = ((BitmapDrawable) ground).getBitmap();
        Bitmap bsky = ((BitmapDrawable) sky).getBitmap();
        bbang = ((BitmapDrawable) bang).getBitmap();

        Bitmap rbground = Bitmap.createScaledBitmap(bground, wpx, hpx, false);
        Bitmap rbsky = Bitmap.createScaledBitmap(bsky, wpx, hpx, false);

        backImages = new BackImages(getApplicationContext(), bground, bsky, wpx);
        frame_container.addView(backImages);

        handler.postDelayed(r, 10);

        for(int i=0; i<10; i++) {
            Random rand = new Random();
            int c = rand.nextInt(4) + 1; //Colore scelto a caso
            String filename = String.format("balloon%d", c);

            Bitmap bballoon = BitmapFactory.decodeResource(
                    this.getResources(),
                    this.getResources().getIdentifier(filename, "drawable", this.getPackageName())
            );

            int bh = (int) (0.55 * rand.nextInt(hpx)) + 50;
            int bw = (int) (0.65 * bh);
            int by = (int) (0.65 * rand.nextInt(hpx));
            int bs = rand.nextInt(20) + 1;
            Bitmap rballoon = Bitmap.createScaledBitmap(bballoon, bw, bh, false);
            Balloon b = new Balloon(getApplicationContext(), rballoon, i, wpx, by, bs);
            balloons.add(b);
            frame_container.addView(b);
        }

        frame_container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int pointer_index;

                switch(motionEvent.getActionMasked())
                {
                    case MotionEvent.ACTION_DOWN:
                        pointer_index = motionEvent.getActionIndex();
                        int x = (int) motionEvent.getX(pointer_index);
                        int y = (int) motionEvent.getY(pointer_index);
                        Log.d("DEBUG", "Bang at x:" + x + " y:" + y);
                        boolean hit = false;
                        for (Balloon b : balloons)
                        {
                            if (b.bang(x,y))
                            {
                                hit = true;
                                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.explosion);
                                mediaPlayer.start();
                                Log.d("DEBUG", "Hit on balloon " + b.getID());
                                frame_container.removeView(b);
                                balloons.remove(b);
                                int p = b.getSPEED()*10 + b.getBHeight();
                                punteggio = punteggio + p;
                                tvPunteggio.setText("Punteggio: " + punteggio);
                                Bitmap rbbang = Bitmap.createScaledBitmap(bbang, b.getBWidth(), b.getBHeight(), false);
                                explosion = new Bang(getApplicationContext(), rbbang, x, y);
                                frame_container.addView(explosion);
                                timer_explosion = 10;
                                break;
                            }
                        }
                        if (!hit)
                        {
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.duck);
                            mediaPlayer.start();
                        }

                }
                return true;
            }
        });
    }
}