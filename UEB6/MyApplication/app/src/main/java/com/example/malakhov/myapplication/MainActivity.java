package com.example.malakhov.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends Activity {
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

        dotView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN != event.getAction()) {
                    return false;
                }
                dots.addDot(event.getX(), event.getY(), Color.CYAN, DOT_DIAMETER);
                return true;
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

    void makeDot(Dots dots, DotView dotView, int color) {
        int pad = (DOT_DIAMETER + 2) * 2;
        dots.addDot(
                DOT_DIAMETER + (random.nextFloat() * (dotView.getWidth() - pad)),
                DOT_DIAMETER + (random.nextFloat() * (dotView.getHeight() - pad)),
                color, DOT_DIAMETER);

    }
    public class TrackingTouchListener implements View.OnTouchListener {
        private final Dots mDots;
        public static final int DOT_DIAMETER = 6;

        public TrackingTouchListener(Dots dots) {
            this.mDots = dots;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    for (int i=0, n = event.getHistorySize();i < n;i++){
                        addDot(mDots, event.getHistoricalX(i), event.getHistoricalY(i), event.getHistoricalPressure(i), event.getHistoricalSize(i));
                    }
                    break;
                default:
                    return false;
            }
            addDot(mDots, event.getX(), event.getY(), event.getPressure(), event.getSize());
            return true;
        }

        private void addDot(Dots mDots, float x, float y, float p, float s) {
            mDots.addDot(x, y, Color.CYAN, (int) ((p * s * DOT_DIAMETER)));
        }
    }
}


 /*
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                for (int i=0, n = event.getHistorySize();i < n;i++){
                    addDot(dots, event.getHistoricalX(i), event.getHistoricalY(i), event.getHistoricalPressure(i), event.getHistoricalSize(i));
                    Log.d("TAG", "action move");
                }
                break;
            default:
                Log.d("TAG","false");
                return false;
        }
        addDot(dots, event.getX(), event.getY(), event.getPressure(), event.getSize());
        return true;
    }

    private void addDot(Dots mDots, float x, float y, float p, float s) {
        mDots.addDot(x, y, Color.CYAN, (int) ((p * s * DOT_DIAMETER)));
        Log.d("TAG","addDot()");
    } */

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } */

