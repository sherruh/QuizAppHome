package com.geektech.quizapp.ui.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp.App;
import com.geektech.quizapp.model.QuizResult;

public class ResultViewModel extends ViewModel {

    MutableLiveData<QuizResult> quizResultMutableLiveData = new MutableLiveData<>();

    public void loadResult(long id){
        QuizResult quizResult = App.historyStorage.getQuizResult(id);
        quizResultMutableLiveData.setValue(quizResult);
    }
}
