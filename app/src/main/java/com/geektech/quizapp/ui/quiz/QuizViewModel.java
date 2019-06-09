package com.geektech.quizapp.ui.quiz;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.geektech.quizapp.App;
import com.geektech.quizapp.data.IQuizRepository;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.ui.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizViewModel extends ViewModel implements ViewModelProvider.Factory {

    MutableLiveData<List<Question>> questionsLiveData = new MutableLiveData<>();
    private List<Question> questionsCache;
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    //private int numberOfCurrentAnswer;
    MutableLiveData<Integer> numberOfCurrentQuestion = new MutableLiveData<>();
    MutableLiveData<Boolean> isFinished = new MutableLiveData<>();

    public QuizViewModel() {
        isLoading.setValue(true);
        questionsCache = new ArrayList<>();
        loadQuestions();
        numberOfCurrentQuestion.setValue(0);
    }



    private void loadQuestions() {
        App.quizRepository.getQuestions(QuizActivity.AMOUNT,"multiple", new IQuizRepository.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                questionsLiveData.setValue(questions);
                questionsCache.addAll(questions);
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(String message) {
                Log.d("ololo", message);
            }
        });
    }

    public void nextQuestion() {
        if(numberOfCurrentQuestion.getValue() + 1 < questionsCache.size() )
            numberOfCurrentQuestion.setValue(numberOfCurrentQuestion.getValue() + 1);
        else isFinished.setValue(true);
    }

    public void setAnswer(String answer){
        questionsCache.get(numberOfCurrentQuestion.getValue()).setSelectedAnswer(answer);
        Log.d("ololo","answer was "+ questionsCache.get(numberOfCurrentQuestion.getValue())
                + " selected answer "+answer);
        nextQuestion();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new QuizViewModel();
    }
}
