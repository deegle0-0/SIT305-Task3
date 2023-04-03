package com.example.task31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.color.utilities.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

public class QuizSection extends AppCompatActivity {

    //importing all buttons and textView
    TextView welcomeText,questionText;
    Button ans1,ans2,ans3,nextQuestion;
    int index,points;
    ArrayList<QuizQuestions> QuestionsList = new ArrayList<>(); // list of questions of type QuizQuestion class

    ProgressBar progressBar;

    SharedPreferences sharedPreferences; // importing this to get the name from the Main Activity



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_section);

        welcomeText = findViewById(R.id.welcomeText);
        questionText = findViewById(R.id.questionText);
        ans1 = findViewById(R.id.answer1);
        ans2 = findViewById(R.id.answer2);
        ans3 = findViewById(R.id.answer3);
        nextQuestion = findViewById(R.id.nextQuestion);
        index = 0;
        points=0;

        // Progress bar
        progressBar = findViewById(R.id.progressBar2);




//        getting name from first screen
        sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String nameVal = sharedPreferences.getString("name","");


        welcomeText.setText("Welcome: " +nameVal); // display name on top of screen



        getQuizQuestions(QuestionsList);
        Collections.shuffle(QuestionsList);
        setData(index);


        //Checking if first option is the answer
        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b = ans1.getText().toString().trim().toLowerCase();
                if(answerTester(b,index))
                {
                    ans1.setBackgroundColor(getResources().getColor(R.color.green));
                }
                else {
                    ans1.setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });

        //checking if 2nd button is the answer
        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b = ans2.getText().toString().trim().toLowerCase();
                if(answerTester(b,index))
                {
                    ans2.setBackgroundColor(getResources().getColor(R.color.green));
                }
                else {
                    ans2.setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });

        //checking if 3rd button is answer
        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b = ans3.getText().toString().trim().toLowerCase();
                if(answerTester(b,index))
                {
                    ans3.setBackgroundColor(getResources().getColor(R.color.green));
                }
                else {
                    ans3.setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });

        // changes question if we click, Also default the colour of the buttons used
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans1.setBackgroundColor(getResources().getColor(R.color.purple_700));
                ans2.setBackgroundColor(getResources().getColor(R.color.purple_700));
                ans3.setBackgroundColor(getResources().getColor(R.color.purple_700));

                index++;

                progressBar.setProgress(progressBar.getProgress()+ (100/QuestionsList.size()));

                if(index == QuestionsList.size())
                {
                    Intent intent = new Intent(QuizSection.this,FinalGame.class);
                    intent.putExtra("points",points);
                    startActivity(intent);
                    finish();
                }

                setData(index);


            }
        });

    }
    private void setData(int index){

        //to check if all questions are done, if so goes to the final page to show the result.
        if(index == QuestionsList.size()) {
            Intent intent = new Intent(QuizSection.this,FinalGame.class);
            intent.putExtra("points",points);
            startActivity(intent);
            finish();
        }
        else{
            questionText.setText(QuestionsList.get(index).getQuestion());
            ans1.setText(QuestionsList.get(index).getOption1());
            ans2.setText(QuestionsList.get(index).getOption2());
            ans3.setText(QuestionsList.get(index).getOption3());
        }
    }

    public boolean answerTester(String b,int p)
    {
        String a = QuestionsList.get(p).getAnswer().trim().toLowerCase();

        if(a.equals(b))
        {
            //if answer is right, it changes colour and increases points
            points++;
            return true;
        }
        else
        {
            points --;
            return false;
        }

    }

    private void getQuizQuestions(ArrayList<QuizQuestions> questions) {
        // can add questions in here easily without any issues.
        QuestionsList.add(new QuizQuestions("what is the unit code?","221","323",
                "305","305"));
        QuestionsList.add(new QuizQuestions("What is this unit about?","AndroidDev","CloudDev",
                "Idk","AndroidDev"));
        QuestionsList.add(new QuizQuestions("How are you today?","Good","Bad",
                "Idk","Idk"));
        QuestionsList.add(new QuizQuestions("Water boils at","bruh","100degree",
                "Idk","100degree"));
        QuestionsList.add(new QuizQuestions("What is our uni","Monash","Deakin",
                "Idk","Deakin"));

    }
}