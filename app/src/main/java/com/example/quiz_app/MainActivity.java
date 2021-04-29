package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    Button st;
    String reg,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        st = (Button)findViewById(R.id.start);
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg = et1.getText().toString();
                name = et2.getText().toString();
                Toast.makeText(getApplicationContext(),"Welcome "+name,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Questions.class);
                i.putExtra("rno",reg);
                i.putExtra("nme",name);
                startActivity(i);
            }
        });
    }
}