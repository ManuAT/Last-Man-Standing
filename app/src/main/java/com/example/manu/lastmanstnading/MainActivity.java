package com.example.manu.lastmanstnading;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.manu.lastmanstnading.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 7200000;

    private TextView mTextViewCountDown;
    private TextView unlock_key;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private  Button uk1;
    private  Button uk2;
    RelativeLayout home;
    RelativeLayout hint;
    RelativeLayout map;
    RelativeLayout video;
    RelativeLayout unlock1;
    RelativeLayout unlock2;
    RelativeLayout finshed;


    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    public long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //setContentView(R.layout.activity_page2);

        mTextViewCountDown = findViewById(R.id.txt2);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);
        uk1 = findViewById(R.id.sbtn);
        uk2 = findViewById(R.id.unkey);
        home =(RelativeLayout)findViewById(R.id.home);
        hint =(RelativeLayout)findViewById(R.id.hint);
        map =(RelativeLayout)findViewById(R.id.map);
        video = (RelativeLayout)findViewById(R.id.video);
        unlock1= (RelativeLayout)findViewById(R.id.unlock1);
        unlock2 = (RelativeLayout)findViewById(R.id.unlock2);
        finshed = (RelativeLayout)findViewById(R.id.finished);
        unlock_key = findViewById(R.id.untxt);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        /*uk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,unlock.class);
                startActivity(intent);
            }
        });*/
        uk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.setVisibility(View.GONE);
                unlock1.setVisibility(View.VISIBLE);
            }
        });
        uk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(unlock_key.getText().toString().equals("777"))
                {
                unlock1.setVisibility(View.GONE);
                unlock2.setVisibility(View.VISIBLE);}
            }
        });
        startTimer();
        updateCountDownText();


    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000)/ (60*60);
        int minutes = (int) ((mTimeLeftInMillis / 1000)%(60*60) )/ 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d",hours ,minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
}
