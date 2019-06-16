package com.geektech.quizapp.ui.history;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp.App;
import com.geektech.quizapp.model.QuizResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class HistoryViewModel extends ViewModel {
    MutableLiveData<List<QuizResult>> quizResultsLiveData = new MutableLiveData<>();

    public void getHistory(){
        List<QuizResult> quizResults = App.historyStorage.getAll();
        Log.d("ololo","History size " + quizResults.size());
        quizResultsLiveData.setValue(quizResults);
    }

}
