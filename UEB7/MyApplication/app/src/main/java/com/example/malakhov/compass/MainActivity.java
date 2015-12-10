package com.example.malakhov.compass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CompassView compassView = (CompassView) findViewById(R.id.compass);
        compassView.setmBearing(45);

    }
}
