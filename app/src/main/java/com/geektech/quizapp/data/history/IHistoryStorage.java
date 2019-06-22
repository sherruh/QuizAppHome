package com.geektech.quizapp.data.history;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp.model.QuizResult;
import com.geektech.quizapp.model.ShortQuizResult;

import java.util.List;

public interface IHistoryStorage {

    long saveQuizResult(QuizResult result);

    QuizResult getQuizResult(long id);

    void deleteQuizResult(long id);

    LiveData<List<QuizResult>> getAll();

    LiveData<List<ShortQuizResult>> getAllShort();

    int deleteAll();
}
