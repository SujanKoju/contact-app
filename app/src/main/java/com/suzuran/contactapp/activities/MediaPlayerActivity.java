package com.suzuran.contactapp.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.suzuran.contactapp.GetDataInBackGround;
import com.suzuran.contactapp.R;

public class MediaPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        //TODO CALLING BACKGROUND TASK
        GetDataInBackGround getDataInBackGround = new GetDataInBackGround();
        getDataInBackGround.execute("Hello WORLD");


        //TODO TO USE MULTI MEDIA
   /*     VideoView videoView = findViewById(R.id.videoView_sample);
        videoView.setVideoPath("https://www.youtube.com/watch?v=jVzC9NWJU-8");
        videoView.start();*/
  /*      mediaPlayer = MediaPlayer.create(this, R.raw.music); // plays music.mp3
        mediaPlayer.start();*/

        new CountDownTimer(10000, 3000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(MediaPlayerActivity.this, "---- TICK TICK ----", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Toast.makeText(MediaPlayerActivity.this, "---- CLOCK DONE ----", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }
}