package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Result extends AppCompatActivity {
    Button restart,rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        restart = (Button)findViewById(R.id.restart);
        rate = (Button)findViewById(R.id.rate);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resrt = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(resrt);
            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rtus = new Intent(getApplicationContext(),Ratings.class);
                startActivity(rtus);
            }
        });
    }
}