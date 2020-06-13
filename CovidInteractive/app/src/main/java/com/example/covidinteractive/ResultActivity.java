package com.example.covidinteractive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    public void restartgame(View view) {

        Intent intent = new Intent(ResultActivity.this, GameActivity.class);
        startActivity(intent);
    }
}
