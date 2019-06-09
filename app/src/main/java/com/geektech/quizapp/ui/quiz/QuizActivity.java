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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.ui.quiz.adapter.QuestionsAdapter;
import com.geektech.quizapp.ui.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuestionsAdapter.OnAnswerClick {

    public static int AMOUNT;
    public static String DIFFICULTY;

    public static void start(Context context, int amount, String difficulty) {
        AMOUNT = amount;
        DIFFICULTY = difficulty;
        Intent intent = new Intent(context,QuizActivity.class);
        context.startActivity(intent);
    }

    private QuizViewModel quizViewModel;
    private ProgressBar progressBarLoading;
    private RecyclerView recyclerQuestions;
    private QuestionsAdapter adapter;
    private TextView textCategory;
    private ImageView imageBack;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textCategory = findViewById(R.id.quiz_text_category);
        imageBack = findViewById(R.id.quiz_image_back);
        initRecycler();

        progressBarLoading = findViewById(R.id.progress_bar_loading);
        Intent intent = getIntent();

        initViewModel();

    }

    private void initViewModel() {

        quizViewModel = ViewModelProviders.of(this,new QuizViewModel())
                .get(QuizViewModel.class);
        quizViewModel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isLoading) {
                if(isLoading){
                    progressBarLoading.setVisibility(View.VISIBLE);
                    textCategory.setVisibility(View.INVISIBLE);
                }
                else {
                    progressBarLoading.setVisibility(View.GONE);
                }
            }
        });
        quizViewModel.questionsLiveData.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                adapter.setData(questions);
            }
        });

        quizViewModel.numberOfCurrentQuestion.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                recyclerQuestions.scrollToPosition(integer);
            }
        });

        quizViewModel.isFinished.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) ResultActivity.start(QuizActivity.this);
            }
        });

        quizViewModel.currentCategory.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textCategory.setText(s);
                textCategory.setVisibility(View.VISIBLE);
            }
        });

        quizViewModel.isBackPressAvailable.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean) imageBack.setVisibility(View.VISIBLE);
                else imageBack.setVisibility(View.INVISIBLE);
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
    public void onClick(String answer, int adapterPosition) {
        quizViewModel.setAnswer(answer);
    }
}
