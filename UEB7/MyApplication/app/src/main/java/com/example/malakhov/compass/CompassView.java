package com.example.malakhov.compass;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by malakhov on 10.12.2015.
 */
public class CompassView extends View {
    private float mBearing;
    private Paint markerOaint;
    private Paint textPaint;
    private Paint circlePaint;
    private String northS;
    private String eastS;
    private String southS;
    private String westS;
    private int textHeight;

    public CompassView(Context context) {
        super(context);
        initCompassView();
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCompassView();
    }

    public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCompassView();
    }
    protected void initCompassView(){
        setFocusable(true);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(getResources().getColor(R.color.my_background));
        circlePaint.setStrokeWidth(1);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        Resources r = this.getResources();
        northS = r.getString(R.string.north);
        eastS = r.getString(R.string.east);
        southS = r.getString(R.string.sourth);
        westS = r.getString(R.string.west);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(r.getColor(R.color.text));

        textHeight = (int) textPaint.measureText("yY");

        markerOaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        markerOaint.setColor(r.getColor(R.color.marker));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = measure(widthMeasureSpec);
        int measureHeight = measure(heightMeasureSpec);

        int d = Math.min(measureWidth, measureHeight);

        setMeasuredDimension(d,d);
    }

    private int measure(int widthMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMeasureSpec == MeasureSpec.UNSPECIFIED){
            result = 200;
        }else {
            result = 200;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int px = getMeasuredWidth() / 2;
        int py = getMeasuredHeight() / 2;

        int radius = Math.min(px, py);

        canvas.drawCircle(px,py, radius, circlePaint);
        canvas.save();
        canvas.rotate(-mBearing, px, py);

        int textWidth = (int) textPaint.measureText("W");
        int cardinalX = px-textWidth/2;
        int cardinalY = py-radius+textHeight;

        for(int i = 0; i < 24; i++){
            canvas.drawLine(px, py - radius, px, py - radius + 10, markerOaint);
            canvas.save();
            canvas.translate(0, textHeight);

            if (i % 6 == 0){
                String dirStr = "";
                switch (i){
                    case 0:
                        dirStr = northS;
                        int arrowY = 2 * textHeight;
                        canvas.drawLine(px, arrowY, px-5, 3 * textHeight, markerOaint);
                        canvas.drawLine(px,arrowY,px+5,3 * textHeight, markerOaint);

                        break;
                    case 6:
                        dirStr = eastS;
                        break;
                    case 12:
                        dirStr = southS;
                        break;
                    case 18:
                        dirStr = westS;
                        break;
                }
                canvas.drawText(dirStr, cardinalX, cardinalY, textPaint);
            }

            else if (i % 3 == 0){
                String angle = String.valueOf(i * 15);
                float angleTextWidth = textPaint.measureText(angle);

                int angleTextX = (int)(px-angleTextWidth/2);
                int angleTexty = py-radius+textHeight;
                canvas.drawText(angle,angleTextX,angleTexty,textPaint);

            }
            canvas.restore();

            canvas.rotate(15, px, py);
        }
        canvas.restore();

    }

    public float getmBearing() {
        return mBearing;
    }

    public void setmBearing(float mBearing) {
        this.mBearing = mBearing;
    }
}
