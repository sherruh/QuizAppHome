package com.geektech.quizapp.ui.quiz.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.model.enums.EType;


public class QuestionViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

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

    public QuestionViewHolder(@NonNull View itemView, QuestionsAdapter.OnAnswerClick listener) {
        super(itemView);
        mListener = listener;

        containerMultiple = itemView.findViewById(R.id.question_multiple_container);
        containerBoolean = itemView.findViewById(R.id.question_boolean_container);

        textQuestion = itemView.findViewById(R.id.question_text);
        textViewAnswer1 = itemView.findViewById(R.id.question_option_1);
        textViewAnswer1.setOnClickListener(this);
        textViewAnswer2 = itemView.findViewById(R.id.question_option_2);
        textViewAnswer2.setOnClickListener(this);
        textViewAnswer3 = itemView.findViewById(R.id.question_option_3);
        textViewAnswer3.setOnClickListener(this);
        textViewAnswer4 = itemView.findViewById(R.id.question_option_4);
        textViewAnswer4.setOnClickListener(this);
        textViewAnswerFalse = itemView.findViewById(R.id.text_option_no);
        textViewAnswerFalse.setOnClickListener(this);
        textViewAnswerTrue = itemView.findViewById(R.id.text_option_yes);
        textViewAnswerTrue.setOnClickListener(this);

        setDefaultBackground();
    }

    public void setDefaultBackground() {

    }


    public void onBind(Question question) {
        textQuestion.setText(Html.fromHtml(question.getQuestion()));

        if (question.getType() == EType.MULTIPLE) showMultipleContainer(question);
        else showBooleanContainer(question);


    }

    private void showBooleanContainer(Question question) {
        containerBoolean.setVisibility(View.VISIBLE);
        containerMultiple.setVisibility(View.GONE);
    }

    private void showMultipleContainer(Question question) {
        containerBoolean.setVisibility(View.GONE);
        containerMultiple.setVisibility(View.VISIBLE);
        textViewAnswer1.setText(Html.fromHtml( question.getAllAnswers().get(0)));
        textViewAnswer2.setText(Html.fromHtml( question.getAllAnswers().get(1)));
        textViewAnswer3.setText(Html.fromHtml( question.getAllAnswers().get(2)));
        textViewAnswer4.setText(Html.fromHtml( question.getAllAnswers().get(3)));
        setDefaultBackground();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.question_option_1:
                mListener.onClick(0,getAdapterPosition());
                break;
            case R.id.question_option_2:
                mListener.onClick(1,getAdapterPosition());
                break;
            case R.id.question_option_3:
                mListener.onClick(2,getAdapterPosition());
                break;
            case R.id.question_option_4:
                mListener.onClick(3,getAdapterPosition());
                break;
            case R.id.text_option_yes:
                mListener.onClick(0,getAdapterPosition());
                break;
            case R.id.text_option_no:
                mListener.onClick(1,getAdapterPosition());
                break;
        }
    }
}
