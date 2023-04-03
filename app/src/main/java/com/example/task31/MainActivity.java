package com.example.task31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button startButton ;

    EditText name;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTextTextPersonName);

        startButton = findViewById(R.id.startButton);

        sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String nameVal = sharedPreferences.getString("name","");

        Log.v("Name: ", nameVal);

        Intent intent = new Intent(MainActivity.this,QuizSection.class);

        name.setText(nameVal);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor myEditor = sharedPreferences.edit();

                if(!name.getText().toString().isEmpty())
                {
                    myEditor.putString("name",name.getText().toString());
                    myEditor.apply();
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(MainActivity.this,"Enter Name!",Toast.LENGTH_LONG).show();
            }
        });
    }
}