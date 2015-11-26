package com.example.malakhov.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import com.example.malakhov.myapplication.Dot;
import com.example.malakhov.myapplication.Dots;


public class DotView extends View {

    private volatile Dots dots;

    public DotView(Context context, Dots dots) {
        super(context);
        this.dots = dots;
        setMinimumWidth(800);
        setMinimumHeight(200);
        setFocusableInTouchMode(true);
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusableInTouchMode(true);
    }

    public DotView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFocusableInTouchMode(true);
    }

    public void setDots(Dots dots) { this.dots = dots; }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec){
        int measureHeight = measureHeight(heightMeasureSpec);
        int measureWigth = measureWidt(widthMeasureSpec);
        setMeasuredDimension(measureHeight,measureWigth);
    }

    private int measureHeight(int measureSpec){
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 500;
        if(specMode == MeasureSpec.AT_MOST){
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }
        return result;
    }
    private int measureWidt(int measureSpec){
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST){
            result = specSize;
        }else if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }
        return  result;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setColor(hasFocus() ? Color.BLUE : Color.GRAY);
        canvas.drawRect(0, 0, getWidth() - 1, getHeight() - 1, paint);

        if (null == dots) { return;}

        paint.setStyle(Style.FILL);
        for (Dot dot : dots.getSafeDots()) {
            paint.setColor(dot.getColor());
            canvas.drawCircle(
                    dot.getX(),
                    dot.getY(),
                    dot.getDiameter(),
                    paint);
        }
    }


}
