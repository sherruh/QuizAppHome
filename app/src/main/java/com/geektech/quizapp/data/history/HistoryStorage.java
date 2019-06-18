package com.geektech.quizapp.data.history;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp.model.QuizResult;

import java.util.List;

public class HistoryStorage implements IHistoryStorage {

    private HistoryDao dao;

    public HistoryStorage(HistoryDao historyDao){
        dao = historyDao;
    }

    @Override
    public long saveQuizResult(QuizResult result) {
        return dao.insert(result);
    }

    @Override
    public QuizResult getQuizResult(long id) {
        return dao.get(id);
    }

    @Override
    public void deleteQuizResult(long id) {
        dao.delete(id);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}
