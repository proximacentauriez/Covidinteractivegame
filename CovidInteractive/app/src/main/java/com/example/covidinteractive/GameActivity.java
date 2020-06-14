package com.example.covidinteractive;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class GameActivity extends AppCompatActivity {


    private TextView number1;
    private TextView number2;
    private TextView operator;
    private TextView score;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private ProgressBar pb;
    private EditText quizQuestion;
    private RadioGroup quizRadioGrp;
    private RadioGroup mathRadioGrp;
    private RadioButton multiChoiceA;
    private RadioButton multiChoiceB;
    private RadioButton multiChoiceC;
    private static final int NO_OF_QUESTIONS = 10;
    private List<Integer> easyQidx,moderateQidx,difficultQidx;

    private AdView mAdView;

    private static List<QnA_struct> selectedQuizQuestions = new ArrayList<QnA_struct>();
    private static int QnA_idx_cnt = 0;
    protected static int scoreCounter = 0;

    MathWorker MW = new MathWorker();
    CountDownTimer timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        QnA_idx_cnt =0;
        scoreCounter = 0;
        selectedQuizQuestions.clear();
        number1 = (TextView)findViewById(R.id.number1);
        number1.setEnabled(false);
        number2 = (TextView)findViewById(R.id.number2);
        number2.setEnabled(false);
        operator = (TextView)findViewById(R.id.operator);
        operator.setEnabled(false);
        mathRadioGrp = (RadioGroup) findViewById(R.id.radioGroup2);
//        score = (TextView)findViewById(R.id.counter);
        quizRadioGrp = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);


        quizQuestion = (EditText)findViewById(R.id.editText9);
        multiChoiceA = (RadioButton) findViewById(R.id.radioOptionA);
        multiChoiceB = (RadioButton) findViewById(R.id.radioOptionB);
        multiChoiceC = (RadioButton) findViewById(R.id.radioOptionC);

        easyQidx = pickQuestionsForQuiz(4, MainActivity.QuizQuestionsEasy);
        moderateQidx = pickQuestionsForQuiz(3, MainActivity.QuizQuestionsMedium);
        difficultQidx = pickQuestionsForQuiz(3, MainActivity.QuizQuestionsDifficult);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        updateUI();
    }

    public void updateUI() {
        QnA_idx_cnt = 0;
        timer = new CountDownTimer(1000000, 6000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (QnA_idx_cnt < NO_OF_QUESTIONS){
                    validateUserInput();
                    displayQuizTop();
                    Mathpartdisplay();
                    displayProgressBar();
                    QnA_idx_cnt++;
                }else if(QnA_idx_cnt == NO_OF_QUESTIONS){
                    this.cancel();
                    validateUserInput();
                    Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFinish() {

            }
        };
        timer.start();

    }

    public void validateUserInput(){
        int id = quizRadioGrp.getCheckedRadioButtonId();
        int id_maths = mathRadioGrp.getCheckedRadioButtonId();
        if(id != -1 && id_maths!= -1)
        {
            RadioButton userSelection = (RadioButton)findViewById(id);
            RadioButton userMathSelection = (RadioButton)findViewById(id_maths);
            if((userSelection.getText().toString().equalsIgnoreCase(selectedQuizQuestions.get(QnA_idx_cnt-1).getAnswer()))
            &&(userMathSelection.getText().toString().equalsIgnoreCase(Integer.toString(MathWorker.answer)))
            ){

                scoreCounter++;
            }
        }
//        score.setText(Integer.toString(scoreCounter));
        quizRadioGrp.clearCheck();
        mathRadioGrp.clearCheck();

    }
    public void displayProgressBar() {
        pb =(ProgressBar) findViewById(R.id.progressBar5);
        /*Animation*/
        ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", 100, 0);
        animation.setDuration(6000);
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
    }

    public void displayQuizTop(){
        quizQuestion.setText(selectedQuizQuestions.get(QnA_idx_cnt).getQuestion());
        Collections.shuffle(selectedQuizQuestions.get(QnA_idx_cnt).sMultiOptions());
        multiChoiceA.setText(selectedQuizQuestions.get(QnA_idx_cnt).sMultiOptions().get(0));
        multiChoiceB.setText(selectedQuizQuestions.get(QnA_idx_cnt).sMultiOptions().get(1));
        multiChoiceC.setText(selectedQuizQuestions.get(QnA_idx_cnt).sMultiOptions().get(2));

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
                    QnA.get(idx).resetHitCounter();
                }
                if(idxQnA.size() == numOfQuestions)
                    break;
                idx++;
                if(idx == QnA.size())
                    break;
            }
            if(idxQnA.size() == numOfQuestions)
                break;
            hitCount++;
            if(hitCount == 6)
                hitCount = 0;
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

    }

    @Override
    public void onBackPressed(){
        /*
            Do Nothing
         */
        Toast.makeText(GameActivity.this,"Go Corona!! Dont Quit Now!!", Toast.LENGTH_LONG).show();

    }
    public void onDestroy() {
        timer.cancel();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.QnA_idx_cnt =0;
        this.scoreCounter = 0;
        this.selectedQuizQuestions.clear();
        super.onDestroy();
    }
}