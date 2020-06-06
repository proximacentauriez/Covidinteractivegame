package com.example.covidinteractive;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private ProgressBar pb;
    private EditText quizQuestion;
    private RadioButton multiChoiceA;
    private RadioButton multiChoiceB;
    private RadioButton multiChoiceC;
    private static final int NO_OF_QUESTIONS = 20;
    private List<Integer> easyQidx,moderateQidx,difficultQidx;

    private static List<QnA_struct> selectedQuizQuestions = new ArrayList<QnA_struct>();
    private static int QnA_idx_cnt = 0;

    MathWorker MW = new MathWorker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        number1 = (TextView)findViewById(R.id.number1);
        number2 = (TextView)findViewById(R.id.number2);
        operator = (TextView)findViewById(R.id.operator);

        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);

        quizQuestion = (EditText)findViewById(R.id.editText9);
        multiChoiceA = (RadioButton) findViewById(R.id.radioOptionA);
        multiChoiceB = (RadioButton) findViewById(R.id.radioOptionB);
        multiChoiceC = (RadioButton) findViewById(R.id.radioOptionC);

        easyQidx = pickQuestionsForQuiz(10, MainActivity.QuizQuestionsEasy);
        moderateQidx = pickQuestionsForQuiz(5, MainActivity.QuizQuestionsMedium);
        difficultQidx = pickQuestionsForQuiz(5, MainActivity.QuizQuestionsDifficult);

        updateUI();
    }

    public void updateUI() {
        QnA_idx_cnt++;
        displayQuizTop();
        Mathpartdisplay();
        displayProgressBar();
        new CountDownTimer(95000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (QnA_idx_cnt < NO_OF_QUESTIONS){
                   displayQuizTop();
                    Mathpartdisplay();
                    displayProgressBar();
                }
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }.start();

    }

    public void displayProgressBar() {
         pb =(ProgressBar) findViewById(R.id.progressBar5);
         /*Animation*/
         ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", 100, 0);
         animation.setDuration(5000);
         animation.setInterpolator(new LinearInterpolator());
         animation.start();
    }

    public void displayQuizTop(){
        quizQuestion.setText(selectedQuizQuestions.get(QnA_idx_cnt).getQuestion());
        Collections.shuffle(selectedQuizQuestions.get(QnA_idx_cnt).sMultiOptions());
        multiChoiceA.setText(selectedQuizQuestions.get(QnA_idx_cnt).sMultiOptions().get(0));
        multiChoiceB.setText(selectedQuizQuestions.get(QnA_idx_cnt).sMultiOptions().get(1));
        multiChoiceC.setText(selectedQuizQuestions.get(QnA_idx_cnt).sMultiOptions().get(2));
        QnA_idx_cnt++;
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
                    selectedQuizQuestions.add(QnA.get(idx));
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

        MW.BottomPart();

        int int1gui = MathWorker.int1;
        int int2gui = MathWorker.int2;
        number1.setText(Integer.toString(int1gui));
        number2.setText(Integer.toString(int2gui));
        operator.setText(MathWorker.operator);
        //        RadioGroup TXMgroup = new RadioGroup();
        radioButton1.setText(Integer.toString(MathWorker.uniqueNumbers.get(0)));
        radioButton2.setText(Integer.toString(MathWorker.uniqueNumbers.get(1)));
        radioButton3.setText(Integer.toString(MathWorker.uniqueNumbers.get(2)));
        radioButton4.setText(Integer.toString(MathWorker.uniqueNumbers.get(3)));

    }

}