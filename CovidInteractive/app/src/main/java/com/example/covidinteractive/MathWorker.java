package com.example.covidinteractive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MathWorker {

    private static int tcounter;
    public static int int1;
    public static int int2;
    public static String operator;
    public static int answer;
    public static int[] arr = new int[4];

    Random rand = new Random();

    public static List<Integer> uniqueNumbers = new ArrayList<>();
    
    public void BottomPart() {
        uniqueNumbers.clear();
        int var1, var2;

//        String divide = "/";

        int Qcounter = 0;

        MathMethods math = new MathMethods();
        Random rand = new Random();

        if(Qcounter <= 5){

            int1 = rand.nextInt(9);
            int2 = rand.nextInt(9);
        }
        else if(Qcounter > 5 && Qcounter <= 10){

            int1 = rand.nextInt(21);
            int2 = rand.nextInt(21);
        }

        else if(Qcounter > 40 && Qcounter < 60){

            int1 = rand.nextInt(49);
            int2 = rand.nextInt(49);
        }

        int num1 = rand.nextInt(3); // 0, 1, 2

        switch(num1)
        {
            case 0:
                answer = math.add(int1, int2);
                operator = "+";
                break;

            case 1:
                if(int1 < int2){
                    int temp = 0;
                    temp = int1;
                    int1 = int2;
                    int2 = temp;
                }
                answer = math.sub(int1, int2);
                operator = "-";
                break;

            case 2:
                answer = math.mul(int1, int2);
                operator = "*";
                break;
        }
        AddNumberToList(answer);
        int ans1 = -1;
        while (true) {
            if(uniqueNumbers.size() == 3)
                break;

//            ans1 = rand.nextInt(19);
             // while(ans1 > 0)

            while((ans1 = randInt(answer - 10, answer + 10)) < 0)
            {

            }
                AddNumberToList(ans1);

        }

        Collections.shuffle(uniqueNumbers);

    }

    public void AddNumberToList(int num)
    {
        if(!uniqueNumbers.contains(num)) {
            uniqueNumbers.add(num);
        }
    }

    public int randInt(int min, int max) {

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
