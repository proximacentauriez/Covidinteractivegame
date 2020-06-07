package com.example.covidinteractive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button Start_game;
    private static boolean preLoaded= false;
    public static List<QnA_struct> QuizQuestionsEasy;
    public static List<QnA_struct> QuizQuestionsMedium;
    public static List<QnA_struct> QuizQuestionsDifficult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!preLoaded)
            loadQuizQuestions();
    }

    public void startgame(View view) {

        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }
    public void loadQuizQuestions()  {
        String jsonCovidQuestionsEasy = loadJSONFromAsset("covid19_questions_easy.json");
        String jsonCovidQuestionsMedium = loadJSONFromAsset("covid19_questions_medium.json");
        String jsonCovidQuestionsDifficulty = loadJSONFromAsset("covid19_questions_difficult.json");

        Gson gson = new Gson();
        TypeToken<List<QnA_struct>> token = new TypeToken<List<QnA_struct>>() {};
        QuizQuestionsEasy = gson.fromJson(jsonCovidQuestionsEasy, token.getType());
        QuizQuestionsMedium = gson.fromJson(jsonCovidQuestionsMedium, token.getType());
        QuizQuestionsDifficult = gson.fromJson(jsonCovidQuestionsDifficulty, token.getType());
        preLoaded = true;
    }

    public String loadJSONFromAsset(String sfileName) {
        String json = null;
        try {
            InputStream is = getAssets().open(sfileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
