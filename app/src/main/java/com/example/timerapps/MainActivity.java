package com.example.timerapps;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Chronometer stopwatch;
    private boolean running;
    private long pauseOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopwatch = findViewById(R.id.stopwatch);
        stopwatch.setFormat("%s");
        stopwatch.setBase(SystemClock.elapsedRealtime());

        stopwatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if((SystemClock.elapsedRealtime() - stopwatch.getBase()) >= 10000){
                    stopwatch.setBase(SystemClock.elapsedRealtime());
                }
            }
        });
    }

    public void startS(View view){
        if(!running){
            stopwatch.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            stopwatch.start();
            running = true;
        }
    }

    public void pauseS(View view){
        if(running){
            stopwatch.stop();
            pauseOffset = SystemClock.elapsedRealtime() - stopwatch.getBase();
            running = false;
        }
    }

    public void resetS(View view){
        stopwatch.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

}
