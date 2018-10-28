package com.example.hp.timerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {


    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SeekBar timeSeekBar=(SeekBar)findViewById(R.id.seekBar);

        timeSeekBar.setMax(60000);

        new CountDownTimer(60000,1000){
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.timeup);
                mediaPlayer.start();
            }
        }.start();
    }
}
