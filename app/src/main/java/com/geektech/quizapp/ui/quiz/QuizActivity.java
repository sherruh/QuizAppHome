package com.geektech.quizapp.ui.quiz;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.ui.quiz.adapter.QuestionsAdapter;
import com.geektech.quizapp.ui.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuestionsAdapter.OnAnswerClick {

    public static void start(Context context, int amount) {
        Intent intent = new Intent(context,QuizActivity.class);
        intent.putExtra("amount",amount);
        context.startActivity(intent);
    }

    private QuizViewModel quizViewModel;
    private ProgressBar progressBarLoading;
    private RecyclerView recyclerQuestions;
    private QuestionsAdapter adapter;
    int amount;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initRecycler();

        progressBarLoading = findViewById(R.id.progress_bar_loading);
        Intent intent = getIntent();
        amount = intent.getIntExtra("amount",10);
        quizViewModel = ViewModelProviders.of(this,new QuizViewModel(amount))
                .get(QuizViewModel.class);
        quizViewModel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isLoading) {
                if(isLoading) progressBarLoading.setVisibility(View.VISIBLE);
                else progressBarLoading.setVisibility(View.GONE);
            }
        });
        quizViewModel.questionsLiveData.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                adapter.setData(questions);
            }
        });
    }

    private void initRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        recyclerQuestions = findViewById(R.id.recycler_questions);
        recyclerQuestions.setLayoutManager(linearLayoutManager);
        adapter = new QuestionsAdapter(new ArrayList<>(),this);
        recyclerQuestions.setAdapter(adapter);
    }


    @Override
    public void onNextClick(String answer, int adapterPosition) {
        quizViewModel.setAnswer(answer);
        if(adapterPosition + 1 < amount) {
            recyclerQuestions.scrollToPosition(adapterPosition + 1);
            quizViewModel.onNextClick();
        }else ResultActivity.start(this);
    }
}
