package com.example.malakhov.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Random;

/**
 * Created by malakhov on 20.11.2015.
 */
public class TouchMe extends Activity{
    public static final int DOT_DIAMETER = 6;
    private final Random random = new Random();
    final Dots dots = new Dots();
    DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = new DotView(this, dots);

        //install view
        ((LinearLayout) findViewById(R.id.root)).addView(dotView, 0);

        ((Button) findViewById(R.id.button)).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDot(dots, dotView, Color.RED);
            }
        });
        ((Button) findViewById(R.id.button2)).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDot(dots, dotView, Color.GREEN);
            }
        });

        final EditText editText = (EditText) findViewById(R.id.text1);
        final EditText editText2 = (EditText) findViewById(R.id.text2);
        dots.setDotsChangeListener(new Dots.DotsChangeListener() {
            @Override
            public void onDotsChanged(Dots dots) {
                Dot dot = dots.getLastDot();
                editText.setText((null == dot) ? "" : String.valueOf(dot.getX()));
                editText2.setText((null == dot) ? "" : String.valueOf(dot.getY()));
                dotView.invalidate();
            }
        });
    }

    void makeDot(Dots dots, DotView dotView,int color){
        int pad = (DOT_DIAMETER + 2) * 2;
        dots.addDot(
                DOT_DIAMETER + (random.nextFloat() * (dotView.getWidth() - pad)),
                DOT_DIAMETER + (random.nextFloat() * (dotView.getHeight() - pad)),
                color, DOT_DIAMETER);

    }
}
