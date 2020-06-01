package com.example.covidinteractive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


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
        List<Integer> easyQidx = pickQuestionsForQuiz(6, MainActivity.QuizQuestionsEasy);
        List<Integer> moderateQidx = pickQuestionsForQuiz(2, MainActivity.QuizQuestionsMedium);
//        List<Integer> difficultQidx = pickQuestionsForQuiz(2, MainActivity.QuizQuestionsDifficult);
    }

    public List<Integer> pickQuestionsForQuiz(int numOfQuestions, List<QnA_struct> QnA){
        List<Integer> idxQnA = new ArrayList<Integer>();
        //Fetch Index for Easy Questions
        Collections.shuffle(QnA);
        int hitCount = 0;
        while(true) {
            int idx=0;
            while(true){
                if (QnA.get(idx).getHitCounter() == hitCount) {
                    idxQnA.add(idx);
                    QnA.get(idx).incHitCounter();
                }
                if (QnA.get(idx).getHitCounter() == 6) {
                    hitCount = 0;
                    QnA.get(idx).resetHitCounter();
                }
                if(idx == idxQnA.size())
                    break;
                if(idxQnA.size() == numOfQuestions)
                    break;
                idx++;
            }
            if(idxQnA.size() == numOfQuestions)
                break;
            hitCount++;
        }
        return idxQnA;
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