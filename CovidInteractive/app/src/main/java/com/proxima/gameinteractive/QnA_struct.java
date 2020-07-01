package com.proxima.gameinteractive;

import java.util.List;

public class QnA_struct {
    private String sQuestion;
    private String sAnswer;
    private List<String> sMultiOptions;
    private int iHitCounter;


    public String getQuestion(){
        return sQuestion;
    }
//
    public void incHitCounter(){
        iHitCounter++;
    }
//

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
