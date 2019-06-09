package com.geektech.quizapp.ui.quiz.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.Question;

import java.util.Random;


public class MultipleViewHolder extends RecyclerView.ViewHolder {

    QuestionsAdapter.OnAnswerClick mListener;
    TextView textQuestion;
    TextView textViewAnswer1;
    TextView textViewAnswer2;
    TextView textViewAnswer3;
    TextView textViewAnswer4;
    CardView cardViewAnswer1;
    CardView cardViewAnswer2;
    CardView cardViewAnswer3;
    CardView cardViewAnswer4;
    Button buttonSkip;
    String selectedAnswer;


    public MultipleViewHolder(@NonNull View itemView, QuestionsAdapter.OnAnswerClick listener) {
        super(itemView);
        selectedAnswer = "";
        mListener = listener;

        buttonSkip = itemView.findViewById(R.id.button_skip);
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick("",getAdapterPosition());
            }
        });

        textQuestion = itemView.findViewById(R.id.text_question);
        cardViewAnswer1 = itemView.findViewById(R.id.card_view_answer1);
        cardViewAnswer2 = itemView.findViewById(R.id.card_view_answer2);
        cardViewAnswer3 = itemView.findViewById(R.id.card_view_answer3);
        cardViewAnswer4 = itemView.findViewById(R.id.card_view_answer4);
        textViewAnswer1 = itemView.findViewById(R.id.text_answer1);
        textViewAnswer2 = itemView.findViewById(R.id.text_answer2);
        textViewAnswer3 = itemView.findViewById(R.id.text_answer3);
        textViewAnswer4 = itemView.findViewById(R.id.text_answer4);
        setListeners();
        setDefaultBackground();
    }

    private void setDefaultBackground() {

    }

    private void setListeners() {
        cardViewAnswer1.setOnClickListener(v -> {
            setDefaultBackground();
            v.setBackgroundResource(R.color.colorAccent);
            selectedAnswer = textViewAnswer1.getText().toString();
            applyAnswer();
        });
        cardViewAnswer2.setOnClickListener(v -> {
            setDefaultBackground();
            v.setBackgroundResource(R.color.colorAccent);
            selectedAnswer = textViewAnswer2.getText().toString();
            applyAnswer();
        });
        cardViewAnswer3.setOnClickListener(v -> {
            setDefaultBackground();
            v.setBackgroundResource(R.color.colorAccent);
            selectedAnswer = textViewAnswer3.getText().toString();
            applyAnswer();
        });
        cardViewAnswer4.setOnClickListener(v -> {
            setDefaultBackground();
            v.setBackgroundResource(R.color.colorAccent);
            selectedAnswer = textViewAnswer4.getText().toString();
            applyAnswer();
        });

    }

    private void applyAnswer() {
        mListener.onClick(selectedAnswer,getAdapterPosition());
    }

    public void onBind(Question question) {
        textQuestion.setText(question.getQuestion());
        TextView[] textViews = new TextView[]{
                textViewAnswer1,textViewAnswer2
                ,textViewAnswer3,textViewAnswer4
        };
        Random rnd = new Random();
        int correctAnswerPostion = rnd.nextInt(4);
        Log.d("ololo", "correct "+ String.valueOf(correctAnswerPostion));

        for(int i = 0;i<3;i++){
            textViews[i].setText(question.getIncorrectAnswers().get(i));
        }

        if( correctAnswerPostion == 3) textViews[correctAnswerPostion].setText(question.getCorrectAnswer());
        else{
            textViews[3].setText(question.getIncorrectAnswers().get(correctAnswerPostion));
            textViews[correctAnswerPostion].setText(question.getCorrectAnswer());
        }
        setDefaultBackground();
    }
}
