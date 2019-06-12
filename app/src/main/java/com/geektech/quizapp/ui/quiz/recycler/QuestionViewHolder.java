package com.geektech.quizapp.ui.quiz.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.model.enums.Type;

import java.util.Random;


public class QuestionViewHolder extends RecyclerView.ViewHolder {

    QuestionsAdapter.OnAnswerClick mListener;
    TextView textQuestion;
    TextView textViewAnswer1;
    TextView textViewAnswer2;
    TextView textViewAnswer3;
    TextView textViewAnswer4;
    TextView textViewAnswerFalse;
    TextView textViewAnswerTrue;
    LinearLayout containerMultiple;
    LinearLayout containerBoolean;

    String selectedAnswer;

    public QuestionViewHolder(@NonNull View itemView, QuestionsAdapter.OnAnswerClick listener) {
        super(itemView);
        selectedAnswer = "";
        mListener = listener;

        containerMultiple = itemView.findViewById(R.id.question_multiple_container);
        containerBoolean = itemView.findViewById(R.id.question_boolean_container);

        textQuestion = itemView.findViewById(R.id.question_text);
        textViewAnswer1 = itemView.findViewById(R.id.question_option_1);
        textViewAnswer2 = itemView.findViewById(R.id.question_option_2);
        textViewAnswer3 = itemView.findViewById(R.id.question_option_3);
        textViewAnswer4 = itemView.findViewById(R.id.question_option_4);
        textViewAnswerFalse = itemView.findViewById(R.id.question_option_false);
        textViewAnswerTrue = itemView.findViewById(R.id.question_option_true);

        setListeners();
        setDefaultBackground();
    }

    private void setDefaultBackground() {

    }

    private void setListeners() {

    }

    private void applyAnswer() {
        mListener.onClick(selectedAnswer,getAdapterPosition());
    }

    public void onBind(Question question) {
        textQuestion.setText(question.getQuestion());

        if (question.getType() == Type.MULTIPLE) showMultipleContainer(question);
        else showBooleanContainer(question);


    }

    private void showBooleanContainer(Question question) {
        containerBoolean.setVisibility(View.VISIBLE);
        containerMultiple.setVisibility(View.INVISIBLE);
    }

    private void showMultipleContainer(Question question) {
        containerBoolean.setVisibility(View.INVISIBLE);
        containerMultiple.setVisibility(View.VISIBLE);
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
