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

    MutableLiveData<List<Question>> questionsLiveData = new MutableLiveData<>();
    private List<Question> questionsCache;
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    MutableLiveData<Integer> numberOfCurrentQuestion = new MutableLiveData<>();
    MutableLiveData<Boolean> isFinished = new MutableLiveData<>();
    MutableLiveData<String> currentCategory = new MutableLiveData<>();
    MutableLiveData<Boolean> isBackPressAvailable = new MutableLiveData<>();

    public QuizViewModel() {
        isLoading.setValue(true);
        questionsCache = new ArrayList<>();
        loadQuestions();
        numberOfCurrentQuestion.setValue(0);
        isBackPressAvailable.setValue(false);
    }



    private void loadQuestions() {
        App.quizRepository.getQuestions(QuizActivity.AMOUNT,"multiple", QuizActivity.DIFFICULTY, new IQuizRepository.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                questionsLiveData.setValue(questions);
                questionsCache.addAll(questions);
                isLoading.setValue(false);
                currentCategory.setValue(questionsCache.get(numberOfCurrentQuestion.getValue()).getCategory());
            }

            @Override
            public void onFailure(String message) {
                Log.d("ololo", message);
            }
        });
    }

    public void nextQuestion() {
        if(numberOfCurrentQuestion.getValue() + 1 < questionsCache.size() ){
            if (isBackPressAvailable.getValue() == false) isBackPressAvailable.setValue(true);
            numberOfCurrentQuestion.setValue(numberOfCurrentQuestion.getValue() + 1);
            currentCategory.setValue(questionsCache.get(numberOfCurrentQuestion.getValue()).getCategory());
        }
        else isFinished.setValue(true);
    }

    public void setAnswer(String answer){
        questionsCache.get(numberOfCurrentQuestion.getValue()).setSelectedAnswer(answer);
        Log.d("ololo","question was "+ questionsCache.get(numberOfCurrentQuestion.getValue())
                + " difficulty was " +questionsCache.get(numberOfCurrentQuestion.getValue()).getDifficulty()
                + " selected answer "+answer);
        answer = "";
        nextQuestion();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new QuizViewModel();
    }
}
