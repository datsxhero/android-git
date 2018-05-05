package com.example.user.myapplication;

import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable weatherGif;
    AnimationDrawable typhoonGif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton weatherImage = (ImageButton) findViewById(R.id.weatherbtn);
        ImageButton typhoonImage = (ImageButton) findViewById(R.id.typhoonbtn);
        weatherImage.setBackgroundResource(R.drawable.rain);
        typhoonImage.setBackgroundResource(R.drawable.typhoon);
        weatherGif = (AnimationDrawable) weatherImage.getBackground();
        typhoonGif = (AnimationDrawable) typhoonImage.getBackground();
    }
    @Override
    protected void onStart() {
        super.onStart();
        weatherGif.start();
        typhoonGif.start();
    }
}
