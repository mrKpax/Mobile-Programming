package com.example.balloons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Balloon extends View {
    private Bitmap balloon;
    private int x, y;
    private int SPEED;
    private int WIDTH;
    private int ID;
    private int centerx, centery;
    private int bh, bw;

    public Balloon(Context c, Bitmap balloon, int id, int w, int y, int speed)
    {
        super(c);
        this.balloon = balloon;
        WIDTH = w;
        SPEED = speed;
        this.y = y;
        this.x = WIDTH;
        this.ID = id;
        bw = balloon.getWidth();
        bh = balloon.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Paint p = new Paint();
        canvas.drawBitmap(balloon, x, y, p);
        x = x-SPEED;
        if (x<0) x = WIDTH;
        p.setColor(Color.BLACK);
        p.setTextSize(40);
        canvas.drawText("" + ID, x+bw/2, y+bh/2, p);
    }

    public int getID(){
        return ID;
    }

    public int getSPEED()
    {
        return SPEED;
    }

    public int getBHeight()
    {
        return bh;
    }

    public int getBWidth()
    {
        return bw;
    }

    public boolean bang(int x, int y)
    {
        centerx = this.x + bw/2;
        centery = this.y + bh/2;

        int xdelta = Math.abs(centerx-x);
        int ydelta = Math.abs(centery-y);
        if ((xdelta < 0.4*bw) && (ydelta < 0.4*bh))
        {
            return true;
        }
        return false;
    }
}
