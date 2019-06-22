package com.geektech.quizapp.ui.history;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp.App;
import com.geektech.quizapp.model.QuizResult;
import com.geektech.quizapp.model.ShortQuizResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class HistoryViewModel extends ViewModel {

    LiveData<List<ShortQuizResult>> history = App.historyStorage.getAllShort();

}
