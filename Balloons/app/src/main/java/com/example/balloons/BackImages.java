package com.example.balloons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class BackImages extends View {

    private Bitmap ground, sky;
    private int xs, xg, ys, yg = 0;
    private int WIDTH;
    private int SPEEDG = 5;
    private int SPEEDS = 10;
    public BackImages(Context c, Bitmap ground, Bitmap sky, int w)
    {
        super(c);
        this.ground = ground;
        this.sky = sky;
        this.WIDTH = w;
        xs = 0;
        ys = 0;
        xg = 0;
        yg = 0;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Paint p = new Paint();
        canvas.drawBitmap(sky, xs, ys, p);
        canvas.drawBitmap(sky, xs+WIDTH, ys, p);
        canvas.drawBitmap(ground, xg, yg, p);
        canvas.drawBitmap(ground, xg+WIDTH, yg, p);
        xg = xg - SPEEDG;
        if ( xg <- WIDTH) xg=0;
        xs = xs - SPEEDS;
        if ( xs <- WIDTH) xs=0;
    }
}
