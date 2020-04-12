package com.example.drivingtest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionsActivity extends AppCompatActivity {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    String questions[] = {
            "Choose the best answer from the following options regarding lane changing:",
            "Which of the following regions have a maximum speed of 100km/h?",
            "When a driver wishes to make a U-turn, s/he must consider:",
            "After successful completion of motorcycle balance, written and vision test, which of the following licenses is issued to the applicant?",
            "What type of parking is suitable near shopping complexes and heavy traffic areas?",
            "What is the required safe distance when two vehicles overtake?",
    };
    String answers[] = {"Never attempt to change lanes at the last moment or at an intersection",
            "Paved portions of the Trans Canada Highway",
            "That the road must be wide enough and no other traffic is passing near",
            "Class 6 Level I license",
            "Angle parking",
            "One hundred and fifty meters or less"
    };
    String opt[] = {
            "Make the lane change attempt at the last moment to avoid collision","Never attempt to change lanes driving on a highway","Make a sharp turn at an intersection and change lanes","Never attempt to change lanes at the last moment or at an intersection",
            "Paved highways of Newfoundland and Labrador","Gravel roads of Newfoundland and Labrador","Paved portions of the Trans Canada Highway","Unpaved roads of Trans Canada Highway",
            "That the road must be wide enough and no other traffic is passing near","That the location must be a curve or an intersection","That there is a \"U-turn\" sign","That the road must be narrow with no intersection",
            "Class 5 Level I license","Class 6 Level I license","Class 6 Level II license","Class 1 Drivers license",
            "Angle parking","Parallel parking","Hill parking","Straight line parking",
            "Three hundred meters or more","Thirty meters or less","Two hundred meters or more","One hundred and fifty meters or less"
    };
    int flag=0;
    public static int marks=0,correct=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        final TextView score = (TextView)findViewById(R.id.textView4);
        TextView textView=(TextView)findViewById(R.id.DispName);
        Intent intent = getIntent();
        String name= intent.getStringExtra("myname");

        if (name.trim().equals(""))
            textView.setText("Hello User");
        else
            textView.setText("Hello " + name);

        submitbutton=(Button)findViewById(R.id.button3);
        quitbutton=(Button)findViewById(R.id.buttonquit);
        tv=(TextView) findViewById(R.id.tvque);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                if(ansText.equals(answers[flag])) {
                    correct++;
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    wrong++;
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;

                if (score != null)
                    score.setText(""+correct);

                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);
            }
        });
    }

}
