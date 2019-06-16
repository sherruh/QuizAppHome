package com.geektech.quizapp.ui.result;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.QuizResult;

public class ResultActivity extends AppCompatActivity {

    private static final String ID = "id";

    public static void start(Context context, long id) {
        Intent intent = new Intent(context,ResultActivity.class);
        intent.putExtra(ResultActivity.ID,id);
        context.startActivity(intent);
    }

    private ResultViewModel resultViewModel;

    private CardView cardViewResult;
    private TextView textDifficulty;
    private TextView textCorrectAnswers;
    private TextView textStats;
    private TextView textCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initView();

        initViewModel();
    }

    private void initView() {
        cardViewResult = findViewById(R.id.card_result_stats);
        textCorrectAnswers = cardViewResult.findViewById(R.id.result_text_stats);
        textDifficulty = cardViewResult.findViewById(R.id.result_text_difficulty);
        textStats = cardViewResult.findViewById(R.id.result_text_result);
        textCategory = cardViewResult.findViewById(R.id.result_text_category);
    }

    private void initViewModel() {
        resultViewModel = new ResultViewModel();
        resultViewModel.loadResult(getIntent().getLongExtra(ID,0));
        resultViewModel.quizResultMutableLiveData.observe(this, new Observer<QuizResult>() {
            @Override
            public void onChanged(QuizResult quizResult) {
                textCorrectAnswers.setText(String.valueOf(quizResult.getCorrectAnswers()));
                textDifficulty.setText(String.valueOf(quizResult.getDifficulty()));
                textStats.setText(String.valueOf(quizResult.getCorrectAnswers()
                        /quizResult.getQuestions().size()
                        *100 + " %"));
                textCategory.setText("Category: " + quizResult.getCategory());
            }
        });
    }
}
