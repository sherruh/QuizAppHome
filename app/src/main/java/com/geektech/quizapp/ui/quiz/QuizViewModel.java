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

import java.util.ArrayList;
import java.util.List;

public class QuizViewModel extends ViewModel implements ViewModelProvider.Factory {

    private int amount;
    MutableLiveData<List<Question>> questionsLiveData = new MutableLiveData<>();
    private List<Question> questionsCache;
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private int numberOfCurrentAnswer;

    public QuizViewModel(int amount) {
        isLoading.setValue(true);
        this.amount = amount;
        questionsCache = new ArrayList<>();
        loadQuestions(amount);
        numberOfCurrentAnswer = 0;
    }



    private void loadQuestions(int amount) {
        App.quizRepository.getQuestions(amount,"multiple", new IQuizRepository.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                for (Question question : questions) {
                   // Log.d("ololo", question.toString());
                }
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

    public void onNextClick() {
        this.numberOfCurrentAnswer +=1;
    }

    public void setAnswer(String answer){
        questionsCache.get(numberOfCurrentAnswer).setSelectedAnswer(answer);
        Log.d("ololo","answer was "+ questionsCache.get(numberOfCurrentAnswer)
                + " selected answer "+answer);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new QuizViewModel(amount);
    }
}
