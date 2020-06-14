package com.example.covidinteractive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ResultActivity extends AppCompatActivity {

    ConstraintLayout cl;

    private AdView mAdView4;
    private AdView mAdView5;

    private TextView scoreCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        cl = (ConstraintLayout)findViewById(R.id.resultid);
        scoreCounter = (TextView)findViewById(R.id.scoreval);
        scoreCounter.setEnabled(false);
        mAdView4 = findViewById(R.id.adView4);
        mAdView5 = findViewById(R.id.adView5);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView4.loadAd(adRequest);
        mAdView5.loadAd(adRequest);

        if(GameActivity.scoreCounter > 8)
        {
            cl.setBackgroundResource(R.drawable.img9);


        }
        else if (GameActivity.scoreCounter <= 8 && GameActivity.scoreCounter > 6)
        {
            cl.setBackgroundResource(R.drawable.img11);
        }
        else
        {
            cl.setBackgroundResource(R.drawable.img10);
        }
        scoreCounter.setText(Integer.toString((GameActivity.scoreCounter) * 10) + "%");
    }

    public void restartgame(View view) {

        Intent intent = new Intent(ResultActivity.this, GameActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        /*
        Do Nothing
         */
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
