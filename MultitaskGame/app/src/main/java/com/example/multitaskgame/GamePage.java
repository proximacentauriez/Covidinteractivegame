package com.example.multitaskgame;

import androidx.appcompat.app.AppCompatActivity;
import com.example.multitaskgame.R;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class GamePage extends AppCompatActivity {

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
        setContentView(R.layout.activity_game_page);
    }

    public void Mathpartdisplay(){

        int int1gui = Mathworker.int1;
//        String oper = Mathworker
        int int2gui = Mathworker.int2;

        number1.setText(Integer.toString(int1gui));
        number2.setText(Integer.toString(int2gui));
        operator.setText(Mathworker.operator);

//        RadioGroup TXMgroup = new RadioGroup();

        radioButton1.setText(Mathworker.arr[0]);
        radioButton2.setText(Mathworker.arr[1]);
        radioButton3.setText(Mathworker.arr[2]);
        radioButton4.setText(Mathworker.arr[3]);

    }
}