package com.example.balloons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Bang extends View {
    private Bitmap bang;
    private int x, y;
    private int bh, bw;

    public Bang(Context c, Bitmap bang, int x, int y)
    {
        super(c);
        this.bang = bang;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Paint p = new Paint();
        canvas.drawBitmap(bang, x, y, p);
    }

}
