package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Questions extends AppCompatActivity {
    TextView ques;
    String name,regno;
    int progressStatus=0;
    ProgressBar pb;
    RadioGroup ansgrp;
    Button next,submit;
    RadioButton rb1,rb2,rb3,rb4;
    String questions[] = {"Android operating system was developed by which company?",
            "Beta version of android was launched in which year?",
            "Which Android version is the latest?",
            "First Android phone was launched in India by which company?",
            "What is the name of Android 5?"};
    String answers[] = {"Google","2007","11","HTC","Lollipop"};
    String options[] = {"Apple","Microsoft","Google","Tesla",
                        "2006","2007","2008","2009",
                        "10","11","12","13",
                        "HTC","Motorola","L.G.","Samsung",
                        "KitKat","Oreo","Lollipop","Marshmellow"};
    int flag=0;
    static int marks=0,crt=0,wrng=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        TextView nm = (TextView)findViewById(R.id.dname);
        TextView rn = (TextView)findViewById(R.id.dreg);
        Intent i = getIntent();
        regno = i.getStringExtra("rno");
        rn.setText("Registration Number: "+regno);
        name = i.getStringExtra("nme");
        nm.setText("Name: "+name);
        pb = (ProgressBar)findViewById(R.id.pb);
        next = (Button)findViewById(R.id.next);
        submit = (Button)findViewById(R.id.submit);
        ques = (TextView)findViewById(R.id.ques);
        ansgrp = (RadioGroup)findViewById(R.id.ansgrp);
        rb1= (RadioButton)findViewById(R.id.rb1);
        rb2= (RadioButton)findViewById(R.id.rb2);
        rb3= (RadioButton)findViewById(R.id.rb3);
        rb4= (RadioButton)findViewById(R.id.rb4);
        ques.setText(questions[flag]);
        rb1.setText(options[0]);
        rb2.setText(options[1]);
        rb3.setText(options[2]);
        rb4.setText(options[3]);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ansgrp.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton rans = (RadioButton)findViewById(ansgrp.getCheckedRadioButtonId());
                String ans = rans.getText().toString();
                if(ans.equals(answers[flag]))
                    crt++;
                else
                    wrng++;
                flag++;
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus < 2) {
                            progressStatus += 1;
                            pb.setProgress(progressStatus);
                            try {
                                Thread.sleep(1);
                            }
                            catch (InterruptedException a) {
                            }
                        }
                    }
                });
                t.start();
                Toast.makeText(getApplicationContext(),"Submitting answer",Toast.LENGTH_SHORT).show();
                if(flag<questions.length){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressStatus = 0;
                            pb.setProgress(0);
                            ques.setText(questions[flag]);
                            rb1.setText(options[flag*4]);
                            rb2.setText(options[flag*4 +1]);
                            rb3.setText(options[flag*4 +2]);
                            rb4.setText(options[flag*4 +3]);
                        }
                    }, 2000);
                }
                else {
                    marks = crt*10;
                    Toast.makeText(getApplicationContext(), "Quiz complete. \nSelect Submit.", Toast.LENGTH_SHORT).show();
                }
                ansgrp.clearCheck();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater i = getLayoutInflater();
                View l = i.inflate(R.layout.activity_custom_toast,findViewById(R.id.ct));
                TextView t = l.findViewById(R.id.rtv);
                StringBuffer sb = new StringBuffer();
                sb.append("Correct answers: "+crt+"\nWrong answers: "+wrng+"\nMarks Obtained: "+marks);
                t.setText(sb);
                crt = 0;
                wrng = 0;
                marks =0;
                Toast tv = new Toast(getApplicationContext());
                tv.setGravity(Gravity.CENTER_VERTICAL,0,0);
                tv.setDuration(Toast.LENGTH_LONG);
                tv.setView(l);
                tv.show();
                Intent in2 = new Intent(getApplicationContext(),Result.class);
                startActivity(in2);
            }
        });
    }
}