package com.example.covidinteractive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

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