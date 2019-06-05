package com.geektech.quizapp.ui.quiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geektech.quizapp.R;

public class QuizActivity extends AppCompatActivity {

    public static void start(Context context, int amount) {
        context.startActivity(new Intent(context, QuizActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //TODO: Update questions progress
        //TODO: Observer ViewModel LiveData
    }
}
