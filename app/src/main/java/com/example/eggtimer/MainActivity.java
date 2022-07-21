package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView countdownTextView;
    Boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;
    public void resetTimer()
    {
        countdownTextView.setText("00 : 00");
        timerSeekBar.setProgress(0);
        countDownTimer.cancel();
        timerSeekBar.setEnabled(true);
        goButton.setText("Go!");
        counterIsActive = false;
    }
   public void buttonClicked(View view)
   {
       if (counterIsActive)
       {
           resetTimer();
       }
       else
       {
           counterIsActive = true;
           timerSeekBar.setEnabled(false);
           goButton.setText("Stop");

           countDownTimer = new CountDownTimer( timerSeekBar.getProgress() * 1000 , 1000)
           {

               @Override
               public void onTick(long l) {
                   Log.i("Checking" , "Nice man!");
                   updateTimer((int) l/1000);

               }

               @Override
               public void onFinish() {
                   MediaPlayer mpPlayer = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                   mpPlayer.start();
                   resetTimer();
               }
           }.start();
       }



   }



   public void updateTimer(int progress)
   {
       int minute = progress/60;
       int second = progress - (minute * 60);

       String secondString = Integer.toString(second);

       if (second <= 9)
       {
           secondString = "0" + secondString;
       }

       String minuteString = Integer.toString(minute);

       if (minute <= 9)
       {
           minuteString = "0" + minuteString;
       }
       countdownTextView.setText(minuteString + " : " + secondString);
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekbar);
        countdownTextView = findViewById(R.id.countdownTextView);

        goButton = findViewById(R.id.goButton);


        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               updateTimer(progress);
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