package com.example.covidinteractive;

import java.util.List;

public class QnA_struct {
    private String sQuestion;
    private String sAnswer;
    private List<String> sMultiOptions;
    private int iDifficulty; // 0 - EASY 1 - MODERATE 2 - DIFFICULT
    private int iHitCounter;


    public String getQuestion(){
        return sQuestion;
    }
//
    public void incHitCounter(){
        iHitCounter++;
    }
//
    public int  getDiffLevel(){
        return iDifficulty;
    }
//
    public String getAnswer(){
        return sAnswer;
    }
//
    public List<String> sMultiOptions(){
        return sMultiOptions;
    }
//
    public int getHitCounter() {
            return iHitCounter;
    }

    public void resetHitCounter() {
           iHitCounter = 0;
    }
}
