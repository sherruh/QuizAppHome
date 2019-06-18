package com.geektech.quizapp.data.history;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp.model.QuizResult;

import java.util.List;

public interface IHistoryStorage {

    long saveQuizResult(QuizResult result);

    QuizResult getQuizResult(long id);

    void deleteQuizResult(long id);

    LiveData<List<QuizResult>> getAll();

    void deleteAll();
}
