package com.example.hp.timerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    MediaPlayer mediaPlayer;
    TextView textView;
    SeekBar timeSeekBar;
    boolean btnpressed=false;
    CountDownTimer countDownTimer;
    Button button;

    public void resetTimer(){
        btnpressed=false;
        button.setText("Start");
        textView.setText("00:30");
        timeSeekBar.setProgress(30);
        timeSeekBar.setEnabled(true);
        countDownTimer.cancel();
    }

    public void buttonPressed(View view){

        if(btnpressed){
            resetTimer();
        }

        else {

            btnpressed=true;
            timeSeekBar.setEnabled(false);
            button.setText("Stop");
            countDownTimer = new CountDownTimer(timeSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTime((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.timeup);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTime(int sec){
        int minute=sec/60;
        int seconds=sec-(minute*60);

        String secString=Integer.toString(seconds);
        if(seconds<=9){
            secString="0"+secString;
        }

        textView.setText(Integer.toString(minute)+":"+secString);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView=(TextView)findViewById(R.id.textView);
        timeSeekBar=(SeekBar)findViewById(R.id.seekBar);
        button =(Button)findViewById(R.id.button);

        
        timeSeekBar.setMax(600);
        timeSeekBar.setProgress(30);

        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTime(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
