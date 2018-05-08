package com.example.user.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable weatherGif;
    AnimationDrawable typhoonGif;
    AnimationDrawable earthquakeGif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton weatherImage = (ImageButton) findViewById(R.id.weatherbtn);
        weatherImage.setBackgroundResource(R.drawable.rain);
        weatherGif = (AnimationDrawable) weatherImage.getBackground();

//        ImageButton typhoonImage = (ImageButton) findViewById(R.id.typhoonbtn);
//        typhoonImage.setBackgroundResource(R.drawable.typhoon);
//        typhoonGif = (AnimationDrawable) typhoonImage.getBackground();

        ImageButton earthQuake = (ImageButton)findViewById(R.id.earthquakebtn);
        earthQuake.setBackgroundResource(R.drawable.earthquake);
        earthquakeGif = (AnimationDrawable) earthQuake.getBackground();

    }
    @Override
    protected void onStart() {
        super.onStart();
        weatherGif.start();
        //typhoonGif.start();
        earthquakeGif.start();

    }
    public void WeekWeather(View view){
        Intent intent = new Intent(this, WeekWeather.class);
        startActivity(intent);
    }
    public  void TyphoonWarnings(View view){
        Intent intent = new Intent(this, TyphoonWarnings.class);
        startActivity(intent);
    }
    public void EarthquakeWarnings(View view){
        Intent intent = new Intent(this, EarthquakeWarnings.class);
        startActivity(intent);
    }


}
