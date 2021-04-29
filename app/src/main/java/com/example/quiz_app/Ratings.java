package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class Ratings extends AppCompatActivity {
    RatingBar ratingbar;
    Button rtng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);
        ratingbar = (RatingBar)findViewById(R.id.ratingBar);
        rtng = (Button)findViewById(R.id.rtngs);
        rtng.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                //Getting the rating and displaying it on the toast
                String rating=String.valueOf(ratingbar.getRating());
                Toast.makeText(getApplicationContext(), "Thank You for Rating Us with "+rating+" stars.", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent resrt = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(resrt);
                    }
                }, 3000);
            }
        });
    }
}