package com.example.covidinteractive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ResultActivity extends AppCompatActivity {

    ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        cl = (ConstraintLayout)findViewById(R.id.resultid);
        if(GameActivity.scoreCounter > 8){
            cl.setBackgroundResource(R.drawable.img9);
        }else if (GameActivity.scoreCounter < 8 && GameActivity.scoreCounter > 6){
            cl.setBackgroundResource(R.drawable.img11);
        }else{
            cl.setBackgroundResource(R.drawable.img10);
        }

    }

    public void restartgame(View view) {

        Intent intent = new Intent(ResultActivity.this, GameActivity.class);
        startActivity(intent);
    }
}
