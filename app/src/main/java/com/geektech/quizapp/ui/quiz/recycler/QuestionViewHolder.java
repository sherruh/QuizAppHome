package com.geektech.quizapp.ui.quiz.recycler;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.IntArrayEvaluator;
import android.animation.IntEvaluator;
import android.animation.PointFEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.model.enums.EType;
import com.geektech.quizapp.ui.widgets.AnimationEndListener;


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
    int positionOfCorrectAnswer;

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
        textViewAnswer1.setTextColor(itemView.getResources().getColor(R.color.blue));
        textViewAnswer2.setTextColor(itemView.getResources().getColor(R.color.blue));
        textViewAnswer3.setTextColor(itemView.getResources().getColor(R.color.blue));
        textViewAnswer4.setTextColor(itemView.getResources().getColor(R.color.blue));
        textViewAnswerTrue.setTextColor(itemView.getResources().getColor(R.color.blue));
        textViewAnswerFalse.setTextColor(itemView.getResources().getColor(R.color.blue));
    }


    public void onBind(Question question) {
        setDefaultBackground();
        textQuestion.setText(Html.fromHtml(question.getQuestion()));
        if (question.getType() == EType.MULTIPLE) showMultipleContainer(question);
        else showBooleanContainer(question);
        positionOfCorrectAnswer = 0;
        if(question.getType() == EType.BOOLEAN){
            if (question.getCorrectAnswer().equals("True"))positionOfCorrectAnswer = R.id.text_option_yes;
            else positionOfCorrectAnswer = R.id.text_option_no;
        } else{
            for (String answer : question.getAllAnswers()){
                if (answer.equals(question.getCorrectAnswer())){
                   switch (question.getAllAnswers().indexOf(answer)){
                       case 0:
                           positionOfCorrectAnswer = R.id.question_option_1;
                           break;
                       case 1:
                           positionOfCorrectAnswer = R.id.question_option_2;
                           break;
                       case 2:
                           positionOfCorrectAnswer = R.id.question_option_3;
                           break;
                       case 3:
                           positionOfCorrectAnswer = R.id.question_option_4;
                           break;
                    }
                    break;
                }
            }
        }
    }

    private void showBooleanContainer(Question question) {
        containerBoolean.setVisibility(View.VISIBLE);
        containerMultiple.setVisibility(View.GONE);
        setDefaultBackground();
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

    public void showReactionOnSkipping(OnAnimationFinishedCallback callback){

        int colorFrom = itemView.getResources().getColor(R.color.blue);
        int colorTo = itemView.getResources().getColor(R.color.light_green);

        View view = itemView.findViewById(positionOfCorrectAnswer);

        ValueAnimator colorAnimationCorrect;
        colorAnimationCorrect = ValueAnimator.ofObject( new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimationCorrect.setDuration(300);
        colorAnimationCorrect.setRepeatCount(2);
        colorAnimationCorrect.setStartDelay(20);
        colorAnimationCorrect.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ((TextView)view).setTextColor((int)animation.getAnimatedValue());
            }
        });
        colorAnimationCorrect.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation){
                callback.finished();
            }});
        colorAnimationCorrect.start();
    }

    public void showReactionOnCorrectAnswer(View view){

        int colorFrom = view.getResources().getColor(R.color.blue);
        int colorTo = view.getResources().getColor(R.color.light_green);

        ValueAnimator colorAnimationCorrect;
        colorAnimationCorrect = ValueAnimator.ofObject( new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimationCorrect.setDuration(300);
        colorAnimationCorrect.setRepeatCount(2);
        colorAnimationCorrect.setStartDelay(20);
        colorAnimationCorrect.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ((TextView)view).setTextColor((int)animation.getAnimatedValue());
            }
        });
        colorAnimationCorrect.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
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
        });
        colorAnimationCorrect.start();
    }

    public void showReactionOnIncorrectAnswer(View correctAnswer, View incorrectAnswer){
        int colorFrom = itemView.getResources().getColor(R.color.blue);
        int colorToCorrect = itemView.getResources().getColor(R.color.light_green);
        int colorToIncorrect = itemView.getResources().getColor(R.color.red);

        ValueAnimator colorAnimationCorrect, colorAnimationIncorrect;
        colorAnimationCorrect = ValueAnimator.ofObject( new ArgbEvaluator(), colorFrom, colorToCorrect);
        colorAnimationCorrect.setDuration(300);
        colorAnimationCorrect.setRepeatCount(2);
        colorAnimationCorrect.setStartDelay(20);
        colorAnimationCorrect.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ((TextView)correctAnswer).setTextColor((int)animation.getAnimatedValue());
            }
        });

        colorAnimationIncorrect = ValueAnimator.ofObject( new ArgbEvaluator(), colorFrom, colorToIncorrect);
        colorAnimationIncorrect.setDuration(300);
        colorAnimationIncorrect.setRepeatCount(2);
        colorAnimationIncorrect.setStartDelay(20);
        colorAnimationIncorrect.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ((TextView)incorrectAnswer).setTextColor((int)animation.getAnimatedValue());
            }
        });

        colorAnimationIncorrect.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                switch (incorrectAnswer.getId()){
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
        });
        colorAnimationCorrect.start();
        colorAnimationIncorrect.start();
    }

    @Override
    public void onClick(View view) {
        Log.d("ololo", "Postion of correct answer "  + String.valueOf(positionOfCorrectAnswer));

        if (positionOfCorrectAnswer == view.getId()){
            showReactionOnCorrectAnswer(view);
        } else {
            showReactionOnIncorrectAnswer(itemView.findViewById(positionOfCorrectAnswer),view);
        }

    }
}
