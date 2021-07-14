package com.example.firma;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;


public class Lienzo extends View {

    float x,y;
    int opc;
    Paint paint= new Paint();
    Path path = new Path();

    public Lienzo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
    }

    protected void onDraw (Canvas canvas){
        canvas.drawColor(Color.WHITE);
        if (opc==1){
            path.moveTo(x,y);
        }
        if (opc==2){
            path.lineTo(x,y);
        }
        canvas.drawPath(path,paint);
    }

    public boolean onTouchEvent (MotionEvent event) {
        int accion = event.getAction();
        x= event.getX();
        y= event.getY();
        if (accion== MotionEvent.ACTION_DOWN){
            opc= 1;
        }
        if (accion== MotionEvent.ACTION_MOVE){
            opc= 2;
        }
        invalidate();
        return true;
    }

}