package com.geektech.quizapp.ui.quiz;

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

    private static int mAmount;
    private static String mDifficulty;

    private List<Question> questionsCache;
    MutableLiveData<List<Question>> questionsLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    MutableLiveData<Integer> numberOfCurrentQuestion = new MutableLiveData<>();
    MutableLiveData<String> currentCategory = new MutableLiveData<>();
    SingleLiveEvent<Boolean> isFinishedQuiz = new SingleLiveEvent<>();
    SingleLiveEvent<Boolean> isCanceledQuiz = new SingleLiveEvent<>();

    public QuizViewModel(int amount, String difficulty) {

        mAmount = amount;
        mDifficulty = difficulty;
        isLoading.setValue(true);
        questionsCache = new ArrayList<>();
        loadQuestions();
        numberOfCurrentQuestion.setValue(0);

    }

    private void loadQuestions() {
        App.quizRepository.getQuestions(mAmount,"All", mDifficulty, new IQuizRepository.QuestionsCallback() {
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
            numberOfCurrentQuestion.setValue(numberOfCurrentQuestion.getValue() + 1);
            currentCategory.setValue(questionsCache.get(numberOfCurrentQuestion.getValue()).getCategory());
        }
        else isFinishedQuiz.quizFinish();
    }

    public void preqQuestion(){
        if(numberOfCurrentQuestion.getValue() > 0){
            numberOfCurrentQuestion.setValue(numberOfCurrentQuestion.getValue() - 1);
            currentCategory.setValue(questionsCache.get(numberOfCurrentQuestion.getValue()).getCategory());
        }
        else{
            isCanceledQuiz.quizFinish();
        }
    }

    public void setAnswer(String answer){
        questionsCache.get(numberOfCurrentQuestion.getValue()).setSelectedAnswer(answer);
        Log.d("ololo","question was "+ questionsCache.get(numberOfCurrentQuestion.getValue())
                + " difficulty was " +questionsCache.get(numberOfCurrentQuestion.getValue()).getDifficulty()
                + " selected answer "+answer);
        nextQuestion();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new QuizViewModel(mAmount,mDifficulty);
    }
}
