package com.geektech.quizapp.ui.quiz;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.geektech.quizapp.App;
import com.geektech.quizapp.data.IQuizRepository;
import com.geektech.quizapp.model.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {

    //TODO: Create questions live data
    //TODO: Selected answers
    //TODO: Current question position

    public QuizViewModel() {
        //TODO: Show loading
        App.quizRepository.getQuestions(10, new IQuizRepository.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                //TODO: Update LiveData
                //TODO: Hide loading
                for (Question question : questions) {
                    Log.d("ololo", question.toString());
                }
            }

            @Override
            public void onFailure(String message) {
                Log.d("ololo", message);
            }
        });
    }

    public void loadQuestions(int amount) {

    }

    public void onNextClick() {

    }

}
