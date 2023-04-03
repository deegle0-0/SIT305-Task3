package com.example.task31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalGame extends AppCompatActivity {

    TextView pointsScored;
    Button restartQuiz,exitQuiz;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_game);

            int points = getIntent().getExtras().getInt("points");
            pointsScored = findViewById(R.id.pointsShow);
            restartQuiz = findViewById(R.id.restartButton);
            exitQuiz = findViewById(R.id.endButton);


            pointsScored.setText("You scored: "+points + " points");

            sharedPreferences = getSharedPreferences("MyPref",0);
            int sharedPoints = sharedPreferences.getInt("sharedPoints",0);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if(points>sharedPoints){
                sharedPoints = points;
                editor.putInt("sharedPoints",sharedPoints);
                editor.commit();
            }

            restartQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FinalGame.this,QuizSection.class);
                    startActivity(intent);
                    finish();
                }
            });

            exitQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FinalGame.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }
}