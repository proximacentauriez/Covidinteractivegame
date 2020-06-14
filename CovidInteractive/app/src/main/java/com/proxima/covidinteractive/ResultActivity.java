package com.proxima.covidinteractive;

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

        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.shareButton:
                        shareTextUrl();
                        break;
                }
            }
        };
        // our buttons
        findViewById(R.id.shareButton).setOnClickListener(handler);


    }

    public void restartgame(View view) {

        Intent intent = new Intent(ResultActivity.this, GameActivity.class);
        startActivity(intent);
    }

    private void shareTextUrl() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
        String shareMessage= "\nYou May Be Corona, But We Are HomoSapiens!!We will Beat YoU\n";
        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "choose one"));
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
