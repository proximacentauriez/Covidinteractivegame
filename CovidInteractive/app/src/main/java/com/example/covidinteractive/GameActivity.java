package com.example.covidinteractive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class GameActivity extends AppCompatActivity {


    private TextView number1;
    private TextView number2;
    private TextView operator;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private Button submitbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Quizpartdisplay();
    }

    public void Quizpartdisplay()  {
        QnA_struct [] questions;
        String jsonFile = loadJSONFromAsset();
        Gson gson = new Gson();

        questions = gson.fromJson(jsonFile, QnA_struct[].class);


        String question = questions[0].getQuestion();
        List<String> multiOptions  = questions[0].sMultiOptions();
        String answer = questions[0].getAnswer();
        int hitCounter = questions[0].getHitCounter();
        int diffLevel = questions[0].getDiffLevel();

        String question1 = questions[1].getQuestion();
        List<String> multiOptions1  = questions[1].sMultiOptions();
        String answer1 = questions[1].getAnswer();
        int hitCounter1 = questions[1].getHitCounter();
        int diffLevel1 = questions[1].getDiffLevel();
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("covid19_questions.json");
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

    public void Mathpartdisplay(){

        int int1gui = MathWorker.int1;
//        String oper = Mathworker
        int int2gui = MathWorker.int2;

        number1.setText(Integer.toString(int1gui));
        number2.setText(Integer.toString(int2gui));
        operator.setText(MathWorker.operator);

//        RadioGroup TXMgroup = new RadioGroup();

        radioButton1.setText(MathWorker.arr[0]);
        radioButton2.setText(MathWorker.arr[1]);
        radioButton3.setText(MathWorker.arr[2]);
        radioButton4.setText(MathWorker.arr[3]);

    }
}