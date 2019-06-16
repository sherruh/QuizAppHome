package com.geektech.quizapp.data.history;

import com.geektech.quizapp.model.QuizResult;

import java.util.List;

public interface IHistoryStorage {

    long saveQuizResult(QuizResult result);

    QuizResult getQuizResult(long id);

    void deleteQuizResult(int id);

    List<QuizResult> getAll();

    void deleteAll();
}
