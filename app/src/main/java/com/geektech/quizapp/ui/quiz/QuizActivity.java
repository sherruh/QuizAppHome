package com.geektech.quizapp.ui.quiz;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.ui.quiz.recycler.QuestionsAdapter;
import com.geektech.quizapp.ui.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuestionsAdapter.OnAnswerClick {

    public static final String EXTRA_AMOUNT = "amount";
    public static final String EXTRA_DIFFICULTY = "difficulty";

    public static void start(Context context, int amount, String difficulty) {
        Intent intent = new Intent(context,QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT,amount);
        intent.putExtra(EXTRA_DIFFICULTY,difficulty);
        context.startActivity(intent);
    }

    private int amount;
    private String difficulty;

    private QuizViewModel quizViewModel;
    private ProgressBar progressBarLoading;
    private ProgressBar progressBarQuestions;
    private RecyclerView recyclerQuestions;
    private QuestionsAdapter adapter;
    private TextView textCategory;
    private ImageView imageBack;
    private TextView buttonSkip;
    private TextView textProgressOfQuestions;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textCategory = findViewById(R.id.quiz_text_category);
        imageBack = findViewById(R.id.quiz_image_back);
        progressBarLoading = findViewById(R.id.progress_bar_loading);
        progressBarQuestions = findViewById(R.id.progress_bar_questions);
        textProgressOfQuestions = findViewById(R.id.quiz_progress_text);

        buttonSkip = findViewById(R.id.quiz_skip);
        buttonSkip.setOnClickListener(view -> quizViewModel.setAnswer(""));
        imageBack = findViewById(R.id.quiz_image_back);
        imageBack.setOnClickListener(view -> quizViewModel.preqQuestion());

        initRecycler();

        Intent intent = getIntent();
        amount = intent.getIntExtra(EXTRA_AMOUNT,10);
        difficulty = intent.getStringExtra(EXTRA_DIFFICULTY);
        initViewModel();
    }

    private void initViewModel() {

        quizViewModel = ViewModelProviders.of(this,new QuizViewModel(amount,difficulty))
                .get(QuizViewModel.class);
        quizViewModel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isLoading) {
                if(isLoading){
                    progressBarLoading.setVisibility(View.VISIBLE);
                    progressBarQuestions.setVisibility(View.INVISIBLE);
                    textProgressOfQuestions.setVisibility(View.INVISIBLE);
                    textCategory.setVisibility(View.INVISIBLE);
                }
                else {
                    progressBarLoading.setVisibility(View.GONE);
                    progressBarQuestions.setVisibility(View.VISIBLE);
                    textProgressOfQuestions.setVisibility(View.VISIBLE);
                }
            }
        });
        quizViewModel.questionsLiveData.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                adapter.setData(questions);
                progressBarQuestions.setMax(questions.size());
            }
        });

        quizViewModel.numberOfCurrentQuestion.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                recyclerQuestions.smoothScrollToPosition(integer);
                progressBarQuestions.setProgress(integer + 1);
                textProgressOfQuestions.setText(( integer+1) + "/"+ adapter.getItemCount());
            }
        });

        quizViewModel.currentCategory.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textCategory.setText(s);
                textCategory.setVisibility(View.VISIBLE);
            }
        });

        quizViewModel.isFinishedQuiz.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                ResultActivity.start(QuizActivity.this);
            }
        });

        quizViewModel.isCanceledQuiz.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                finish();
            }
        });
    }

    private void initRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                RecyclerView.HORIZONTAL,
                false);
        recyclerQuestions = findViewById(R.id.recycler_questions);
        recyclerQuestions.setLayoutManager(linearLayoutManager);
        recyclerQuestions.setOnTouchListener((view, motionEvent) -> true);
        adapter = new QuestionsAdapter(new ArrayList<>(),this);
        recyclerQuestions.setAdapter(adapter);
    }


    @Override
    public void onClick(String answer, int adapterPosition) {
        quizViewModel.setAnswer(answer);
    }
}
